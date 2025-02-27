package com.feb.net.testlivedata;

import androidx.lifecycle.Observer;

public interface CustomObserver<T> extends Observer<T>,BaseObserver<T>{

    @Override
    void onChanged(T t);

    @Override
    void onFailure(T data);
}
