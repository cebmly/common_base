package com.feb.net.testlivedata;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.feb.lib_utils.LoggerUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomLiveData<T> extends LiveData<T> {
    //数据持有类， 持有的数据
    private T mPendingData=null;
    //观察者的集合
    private List<ObserverWrapper> mObservers=new ArrayList<>();
    private int mVersion = -1;

    /**
     * 注册观察者的方法
     * @param lifecycleOwner
     * @param observer
     */
    public void observer(LifecycleOwner lifecycleOwner, CustomObserver<T> observer){
        //如果当前传进来的组件的生命周期已经结束，就直接返回
        if(lifecycleOwner.getLifecycle().getCurrentState()== Lifecycle.State.DESTROYED){
            return;
        }
        ObserverWrapper mObserverWrapper=new ObserverWrapper();
        mObserverWrapper.observer=observer;
        //为了解决还没 注册的观察者 ，也能监听到的问题
        mObserverWrapper.mLastVersion=-1;
        mObserverWrapper.lifecycle=lifecycleOwner.getLifecycle();
        mObserverWrapper.myLifecycleBound=new MyLifecycleBound();

        mObservers.add(mObserverWrapper);
        lifecycleOwner.getLifecycle().addObserver(mObserverWrapper.myLifecycleBound);
        //有个问题
        //disPatingValue();
    }

    /**
     * 绑定数据 发送通知
     * @param vaule
     */
    public void postValue(T vaule){
        this.mPendingData=vaule;
        mVersion++;
        disPatingValue(true);
    }

    public void postErrorValue(T value) {
        this.mPendingData = value;
        mVersion++;
        disPatingValue(false);
    }

    /**
     * 遍历所有的观察者
     */
    public void disPatingValue(boolean success){

        for (ObserverWrapper mObserverWrapper:mObservers) {
            toChanged(mObserverWrapper,success);
        }
    }
    /**
     * 回调所有的观察者
     */
    public void toChanged(ObserverWrapper mObserverWrapper,boolean success){
        //判断生命周期
        if(mObserverWrapper.lifecycle.getCurrentState()!=Lifecycle.State.RESUMED){
            return;
        }
        //判断匹配版本号，为了防止生命周期改变的时候，观察者会被回调
        if (mObserverWrapper.mLastVersion >= mVersion) {
            return;
        }
        mObserverWrapper.mLastVersion = mVersion;
        if(success) {
            mObserverWrapper.observer.onChanged(mPendingData);
        }else {
            mObserverWrapper.observer.onFailure(mPendingData);
        }
    }

    /**
     * 组件的生命周期的回调类
     */
    @SuppressLint("RestrictedApi")
    class MyLifecycleBound implements GenericLifecycleObserver {
        @Override
        public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
            if(source.getLifecycle().getCurrentState()==Lifecycle.State.DESTROYED){
                remove(source.getLifecycle());
            }
            LoggerUtils.d("TAG-----",source.getLifecycle().getCurrentState().toString());
            if(mPendingData!=null){
                disPatingValue(true);
            }
        }
    }
    /**
     *观察者的封装类
     *
     */
    private class ObserverWrapper{
        //观察者
        CustomObserver<T> observer;
        Lifecycle lifecycle;
        int mLastVersion = -1;
        //绑定生命周期的回调接口
        MyLifecycleBound myLifecycleBound;

    }

    public void remove(Lifecycle lifecycle){
        for (ObserverWrapper mObserverWrapper:mObservers) {
            if(mObserverWrapper.lifecycle==lifecycle){
                mObserverWrapper.lifecycle.removeObserver(mObserverWrapper.myLifecycleBound);
                mObservers.remove(mObserverWrapper);
            }
        }
    }
}
