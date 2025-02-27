package com.feb.net.converter

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.feb.lib_utils.LoggerUtils
import com.feb.net.network.APIException
import com.feb.net.network.api.BaseModel
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

class FastJsonResponseBodyConvert<T>(private val type: Type) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val json = value.string()
        LoggerUtils.d(json)
        value.close()

        if (JSON.parseObject<Any>(json, type) is BaseModel<*>) {
            val obj = JSON.parseObject<BaseModel<*>>(json, type)
            if (obj.code != 200) {
                throw APIException(obj.code, obj.message)
            }
        }
        return JSON.parseObject(json, type)
    }

}