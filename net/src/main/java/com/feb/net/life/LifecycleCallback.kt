package com.feb.net.life

import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.feb.net.callback.Callback
import com.feb.net.exception.HttpError
import java.util.concurrent.atomic.AtomicBoolean

class LifecycleCallback<T> @MainThread constructor(
    private val call: Call<T>,
    private val delegate: Callback<T>,
    private val owner: LifecycleOwner
) : Callback<T>, LifecycleObserver {
    //LifecycleOwner是否被释放
    private val once = AtomicBoolean()
    override fun onStart(call: Call<T>?) {
        if (!once.get()) {
            delegate.onStart(call)
        }
    }

    override fun parseThrowable(call: Call<T>?, t: Throwable?): HttpError? {
        return if (!once.get()) {
            delegate.parseThrowable(call, t)
        } else HttpError("Already disposed", t)
    }

    override fun onFilter(call: Call<T>?, t: T): T? {
        return if (!once.get()) {
            delegate.onFilter(call, t)
        } else t
    }

    override fun onError(call: Call<T>?, error: HttpError?) {
        if (!once.get()) {
            delegate.onError(call, error)
        }
    }

    override fun onSuccess(call: Call<T>?, t: T) {
        if (!once.get()) {
            delegate.onSuccess(call, t)
        }
    }

    override fun onComplete(call: Call<T>?, t: Throwable?) {
        if (!once.get()) {
            delegate.onComplete(call, t)
            owner.lifecycle.removeObserver(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY && once.compareAndSet(false, true)) {
            call.cancel()
            owner.lifecycle.removeObserver(this)
        }
    }

    init {
        //判断发起请求的时候Owner是否已经销毁
        //此时注册生命周期监听不会回调 onDestroy Event
        if (owner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            once.set(true)
            call.cancel()
        } else {
            owner.lifecycle.addObserver(this)
        }
    }
}