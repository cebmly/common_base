package com.feb.lib_common.base;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.feb.lib_common.R;

public abstract class BaseDialog<VDM extends ViewDataBinding> extends Dialog {
    protected VDM mBinding;

    public BaseDialog(@NonNull Context context) {
        this(context, R.style.BaseDialogStyleLocal);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mBinding = DataBindingUtil.bind(LayoutInflater.from(context).inflate(getLayoutId(), null, false));
        if (mBinding != null) {
            setContentView(mBinding.getRoot());
        }
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        initView();
        initData();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mBinding!=null){
            mBinding.unbind();
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();
}
