package com.feb.net;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.feb.net.callback.Callback;
import com.feb.net.exception.HttpError;
import com.feb.net.life.Call;
import com.feb.net.network.APIException;

import java.util.Objects;
import java.util.concurrent.Executor;

import okhttp3.Request;
import retrofit2.HttpException;
import retrofit2.Response;

public class RealCall<T> implements Call<T> {
    private final Executor callbackExecutor;
    private final retrofit2.Call<T> delegate;

    public RealCall(Executor callbackExecutor, retrofit2.Call<T> delegate) {
        this.callbackExecutor = callbackExecutor;
        this.delegate = delegate;
    }

    @Override
    public void cancel() {
        delegate.cancel();
    }

    @Override
    public boolean isCanceled() {
        return delegate.isCanceled();
    }

    @Override
    public Call<T> clone() {
        return new RealCall<>(callbackExecutor, delegate.clone());
    }

    @Override
    public Request request() {
        return delegate.request();
    }

    @Override
    public boolean isExecuted() {
        return delegate.isExecuted();
    }

    @Nullable
    @Override
    public T execute() throws Throwable {
        Response<T> response = delegate.execute();
        T body = response.body();
        if (body != null) {
            return body;
        }
        throw new HttpException(response);
    }

    @Override
    public void enqueue(Callback<T> callback) {
        Objects.requireNonNull(callback, "callback == null");
        callbackExecutor.execute(() -> callback.onStart(RealCall.this));

        delegate.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(retrofit2.Call<T> call, Response<T> response) {
                T body = response.body();
                if (body != null) {
                    body = callback.onFilter(RealCall.this, body);
                }
                if (body != null) {
                    callSuccess(body);
                } else {
                    callFailed(new HttpException(response));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<T> call, Throwable t) {
                callFailed(t);
            }

            private void callSuccess(@NonNull final T body) {
                callbackExecutor.execute(() -> {
                    callback.onSuccess(RealCall.this, body);
                    callback.onComplete(RealCall.this, null);
                });
            }

            private void callFailed(@Nullable final Throwable t) {
                callbackExecutor.execute(() -> {

                    HttpError error = callback.parseThrowable(RealCall.this, t);
                    Objects.requireNonNull(error, "error == null");
                    callback.onError(RealCall.this, error);
                    callback.onComplete(RealCall.this, t);
                });
            }
        });
    }
}
