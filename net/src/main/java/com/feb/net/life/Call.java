package com.feb.net.life;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.feb.net.callback.Callback;

import java.util.Objects;

import okhttp3.Request;

public interface Call<T> extends Cloneable {
    void cancel();
    boolean isCanceled();
    Call<T> clone();
    Request request();
    boolean isExecuted();
    @Nullable
    T execute() throws Throwable;

    /**
     * 异步发送请求，并通知响应{@code callback}回调
     */
    void enqueue(Callback<T> callback);
    default void enqueue(@Nullable LifecycleOwner owner, Callback<T> callback) {
        Objects.requireNonNull(callback,"callback == null");
        enqueue(owner != null? new LifecycleCallback<>(this,callback,owner) : callback);
    }
}