package com.feb.net.adapter

import androidx.lifecycle.LiveData
import com.feb.lib_utils.LoggerUtils
import com.feb.net.network.APIException
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<T>(private val mReasonType: Type, private val isApiResponse: Boolean) :
    CallAdapter<T, LiveData<T>> {
    override fun responseType(): Type {
        return mReasonType
    }

    override fun adapt(call: Call<T>): LiveData<T> {
        return MyLiveData(call, isApiResponse)
    }

    class MyLiveData<T>(private val call: Call<T>, private val isApiResponse: Boolean) :
        LiveData<T>() {
        private val started = AtomicBoolean(false)


        override fun onActive() {
            super.onActive()
            if (started.compareAndSet(false, true)) {
                call.enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        val body = response.body()
                        //发送数据，没有活跃的观察者时不分发，不受线程环境限制
                        postValue(body)
                    }

                    override fun onFailure(call: Call<T>, t: Throwable) {
                        LoggerUtils.e(TAG, t.message)
                        if (isApiResponse) {
                            postValue(APIException(t.message) as T)
                        } else {
                            postValue(null)
                        }
                    }
                })
            }
        }
    }

    companion object {
        private const val TAG = "LiveDataCallAdapter"
    }
}