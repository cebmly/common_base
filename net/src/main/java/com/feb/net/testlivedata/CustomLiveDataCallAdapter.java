package com.feb.net.testlivedata;

import com.feb.net.network.APIException;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomLiveDataCallAdapter<T> implements CallAdapter<T, CustomLiveData<T>> {
    private Type mReasonType;
    private boolean isApiResponse;

    public CustomLiveDataCallAdapter(Type mReasonType, boolean isApiResponse) {
        this.mReasonType = mReasonType;
        this.isApiResponse = isApiResponse;
    }

    @Override
    public Type responseType() {
        return mReasonType;
    }

    @Override
    public CustomLiveData<T> adapt(Call<T> call) {
        return new MyLiveData<>(call,isApiResponse);
    }

    public static class MyLiveData<T> extends CustomLiveData<T> {
        private AtomicBoolean started = new AtomicBoolean(false);
        private final Call<T> call;
        private boolean isApiResponse;

        public MyLiveData(Call<T> call, boolean isApiResponse) {
            this.call = call;
            this.isApiResponse = isApiResponse;

            if(started.compareAndSet(false,true)) {
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(Call<T> call, Response<T> response) {
                        T body = response.body();
                        //发送数据，没有活跃的观察者时不分发，不受线程环境限制
                        postValue(body);
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
//                        if(isApiResponse) {
//                            postValue((T) new APIException(t.getMessage()));
//                        } else {
//                            postValue(null);
//                        }
                        postErrorValue(null);
                    }
                });
            }
        }

        /**
         * 当且仅当有一个活跃的观察者时会触发
         */
        @Override
        protected void onActive() {
            super.onActive();
            if(started.compareAndSet(false,true)) {
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(Call<T> call, Response<T> response) {
                        T body = response.body();
                        //发送数据，没有活跃的观察者时不分发，不受线程环境限制
                        postValue(body);
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
                        if(isApiResponse) {
                            postValue((T) new APIException(t.getMessage()));
                        } else {
                            postValue(null);
                        }
                    }
                });
            }
        }
    }
}
