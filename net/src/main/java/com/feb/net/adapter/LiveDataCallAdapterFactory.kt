package com.feb.net.adapter

import androidx.lifecycle.LiveData
import com.feb.net.network.api.BaseModel
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        //获取第一个泛型类型
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(observableType)
        var isApiResponse = true
        if (rawType != BaseModel::class.java) {
            //返回的
            isApiResponse = false
        }
        
        if (observableType !is ParameterizedType) {
//            throw new IllegalArgumentException("resource must be parameterized");
        }
        return LiveDataCallAdapter<Any>(observableType, isApiResponse)
    }

    companion object {
        private val TAG = LiveDataCallAdapterFactory::class.java.simpleName
    }
}