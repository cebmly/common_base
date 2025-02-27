package com.feb.lib_common.base;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public abstract class BaseBottomSheetDialog<VDM extends ViewDataBinding> extends BottomSheetDialog {

    public Context mContext;
    private Object object;

    protected VDM mBinding;

    public BaseBottomSheetDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        mBinding = DataBindingUtil.bind(LayoutInflater.from(context).inflate(getLayout(), null, false));
        if (mBinding != null) {
            setContentView(mBinding.getRoot());
            ((View) mBinding.getRoot().getParent()).setBackgroundColor(Color.parseColor("#00000000"));
        }
        initView();
        initData();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();

    public void setTag(Object object) {
        this.object = object;
    }

    public Object getTag() {
        return object;
    }
}
