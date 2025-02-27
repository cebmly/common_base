package com.feb.lib_common.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.ScreenUtils;
import com.feb.lib_base.LoadingDialog;
import com.feb.lib_common.R;

public abstract class BaseMvvmDialogFragment<VDB extends ViewDataBinding> extends DialogFragment {
    protected VDB mBinding;
    protected View mContentView;
    private ViewModelProvider mActProvider;
    private ViewModelProvider.Factory mFactory;
    protected LoadingDialog mLoadingDialog;

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
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);

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
        initData();
        initListener();
    }

    protected void initArgs(Bundle arguments) { }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            if (window == null) {
                return;
            }
            window.getDecorView().setClipToOutline(false);
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.setLayout(ScreenUtils.getScreenWidth(), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setWindowAnimations(R.style.CommonShowDialogBottomLocal);
            initDialogStyle(window);
        }

    }
    protected void initDialogStyle(Window window) {

    }

    protected <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> clazz) {
        return new ViewModelProvider(fragment).get(clazz);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinding != null) {
            mBinding.unbind();
        }
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


    public void showLoading() {

        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(activity);
            }
            if (!mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }
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

    public void setCanceledOnTouchOutside(boolean canceled) {
        setCancelable(canceled);
        if (getDialog()!=null) {
            getDialog().setCanceledOnTouchOutside(canceled);
            if (canceled) {
                getDialog().setOnKeyListener(null);
            } else {
                getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            return true;
                        }
                        return false;
                    }
                });
            }
        }
    }
}
