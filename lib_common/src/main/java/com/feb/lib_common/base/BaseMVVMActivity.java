package com.feb.lib_common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.feb.lib_base.LoadingDialog;
import com.feb.lib_utils.ktx.Condition;

import me.jessyan.autosize.AutoSizeCompat;

public  abstract class BaseMVVMActivity<VDB extends ViewDataBinding> extends AppCompatActivity {

    protected Context mContext;
    protected VDB mBinding;
    private ViewModelProvider mActProvider;
    protected ViewModelProvider.Factory mFactory;
    protected LoadingDialog mLoadingDialog;
    private boolean isFullScreen = true;

    public ViewModelProvider getAppViewModelProvider(@NonNull Activity activity) {
        return new ViewModelProvider((BaseApplication) activity.getApplicationContext(), getAppFactory(activity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = activity.getApplication();
        Condition.INSTANCE.ensureNotNull(application, "Your activity/fragment is not yet attached to" +
                "Application. You can't request ViewModel before onCreate call.");
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFullScreen();
        mContext = this;
        if (isFullScreen) {
            BarUtils.setStatusBarLightMode(this, isLightMode());
            BarUtils.transparentStatusBar(this);
            BarUtils.setNavBarColor(this, Color.TRANSPARENT);
            BarUtils.setNavBarLightMode(this, true);
        }
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mBinding.setLifecycleOwner(this);
        ARouter.getInstance().inject(this);
        initView();
        initListener();
        initData();
    }

    protected void showFullScreen() {

    }

    protected void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    protected void initListener() {

    }

    /**
     * 状态栏文字颜色
     *
     * @return
     */
    public boolean isLightMode() {
        return true;
    }

    public void setLightMode(boolean isDark) {
        if (isFullScreen) {
            BarUtils.setStatusBarLightMode(this, isDark);
            BarUtils.transparentStatusBar(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }

    public void showLoading() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void disLoading() {
        try {
            if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public Resources getResources() {
        if (ThreadUtils.isMainThread()) {
            AutoSizeCompat.autoConvertDensityBaseOnWidth(super.getResources(), 375f);
        }
        return super.getResources();
    }
}
