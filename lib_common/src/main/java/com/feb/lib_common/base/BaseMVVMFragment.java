package com.feb.lib_common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.feb.lib_base.LoadingDialog;
import com.feb.lib_utils.ktx.Condition;

public abstract class BaseMVVMFragment<VDB extends ViewDataBinding> extends Fragment {
    protected VDB mBinding;
    protected View mContentView;
    protected LoadingDialog mLoadingDialog;
    protected ViewModelProvider mActProvider;
    private ViewModelProvider.Factory mFactory;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false);
        mContentView = mBinding.getRoot();
        mBinding.setLifecycleOwner(this);
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            initArgs(getArguments());
        }
        initView();
        initListener();
        initData();
    }

    private <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> clazz) {
        return new ViewModelProvider(fragment).get(clazz);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinding != null) {
            mBinding.unbind();
        }
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
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void initListener() {
    }

    protected abstract void initData();

    public void initArgs(Bundle arguments) {

    }

    public void showLoading() {
        Context context = getContext();
        if (context == null)
            return;
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(context);
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

}
