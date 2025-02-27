package com.feb.net.testlivedata;

import androidx.annotation.Nullable;

import com.feb.net.network.api.BaseModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class CustomDataCallAdapterFactory extends CallAdapter.Factory {
    private static final String TAG = CustomDataCallAdapterFactory.class.getSimpleName();

    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != CustomLiveData.class) {
            return null;
        }
        //获取第一个泛型类型
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawType = getRawType(observableType);
        boolean isApiResponse = true;
        if (rawType != BaseModel.class) {
            //返回的
            isApiResponse = false;
        }
        if (!(observableType instanceof ParameterizedType)) {
//            throw new IllegalArgumentException("resource must be parameterized");
        }
        return new CustomLiveDataCallAdapter<>(observableType, isApiResponse);
    }
}