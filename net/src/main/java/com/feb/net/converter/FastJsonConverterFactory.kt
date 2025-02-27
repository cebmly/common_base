package com.feb.net.converter

import com.alibaba.fastjson.serializer.SerializeConfig
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.NullPointerException
import java.lang.reflect.Type

class FastJsonConverterFactory private constructor(serializeConfig: SerializeConfig?) :
    Converter.Factory() {
    private val serializeConfig: SerializeConfig
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        return FastJsonRequestBodyConverter<Any>(serializeConfig)
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return FastJsonResponseBodyConvert<Any>(type)
    }

    companion object {
        @JvmOverloads
        fun create(serializeConfig: SerializeConfig? = SerializeConfig.getGlobalInstance()): FastJsonConverterFactory {
            return FastJsonConverterFactory(serializeConfig)
        }
    }

    init {
        if (serializeConfig == null) throw NullPointerException("serializeConfig == null")
        this.serializeConfig = serializeConfig
    }
}