package com.feb.net.callback

import androidx.annotation.WorkerThread
import com.feb.net.exception.HttpError
import com.feb.net.life.Call

interface Callback<T> {
    fun onStart(call: Call<T>?)

    /**
     * @param call The `Call` that has thrown exception
     * @param t 统一解析throwable对象为HttpError对象，如果Throwable为[HttpError]
     * 则为[retrofit2.Converter.convert]内抛出的异常
     * 如果为[retrofit2.HttpException],
     * 则为[Response.body]为null的时候抛出的
     * @return
     */
    fun parseThrowable(call: Call<T>?, t: Throwable?): HttpError?

    /**
     * 过滤一次数据，如List中的null等，默认返回t
     * @param call
     * @param t
     * @return
     */
    @WorkerThread
    fun onFilter(call: Call<T>?, t: T): T?
    fun onError(call: Call<T>?, error: HttpError?)
    fun onSuccess(call: Call<T>?, t: T)

    /**
     * @param call
     * @param t 请求失败的错误信息
     */
    fun onComplete(call: Call<T>?, t: Throwable?)
}