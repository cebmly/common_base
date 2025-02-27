package com.feb.net.adapter

import com.feb.lib_utils.NetUtils
import com.feb.net.OptionalExecutor
import com.feb.net.RealCall
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.SkipCallbackExecutor
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LifecycleCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        require(returnType is ParameterizedType) {
            String.format(
                "%s return type must be parameterized as %s<Foo> or %s<? extends Foo>",
                RETURN_TYPE,
                RETURN_TYPE,
                RETURN_TYPE
            )
        }
        val responseType = getParameterUpperBound(0, returnType)
        val executor = if (NetUtils.isAnnotationPresent(
                annotations,
                SkipCallbackExecutor::class.java
            )
        ) null else retrofit.callbackExecutor()
        return object : CallAdapter<Any, Any> {
            override fun responseType(): Type {
                return responseType
            }

            override fun adapt(call: Call<Any>): Any {
                return RealCall(executor ?: OptionalExecutor.get(), call)
            }
        }
    }

    companion object {
        private val RETURN_TYPE = Call::class.java.simpleName
        @JvmField
        val INSTANCE: CallAdapter.Factory = LifecycleCallAdapterFactory()
        fun getRawType(type: Type?): Class<*> {
            return CallAdapter.Factory.getRawType(type)
        }
    }
}