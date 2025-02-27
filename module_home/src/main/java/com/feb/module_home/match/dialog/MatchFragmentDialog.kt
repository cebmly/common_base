package com.feb.module_home.match.dialog

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.Gravity
import android.view.Window
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.feb.lib_common.base.BaseMvvmDialogFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.R
import com.feb.module_home.databinding.MatchFragmentDialogBinding

@Route(path = ARouterConstants.MATCH_SCAN_DIALOG)
class MatchFragmentDialog : BaseMvvmDialogFragment<MatchFragmentDialogBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.match_fragment_dialog
    }

    override fun initView() {

    }
    override fun initData() {
//        startRippleAnimation()
    }

    override fun initListener() {
        super.initListener()
        mBinding.llRandom.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.MATCH_SCAN_CALL_ING).navigation()
            dismissAllowingStateLoss()
        }
    }


    private fun startRippleAnimation() {
        val scaleX1 = ObjectAnimator.ofFloat(mBinding.rippleView, "scaleX", 1f, 2f)
        val scaleY1 = ObjectAnimator.ofFloat(mBinding.rippleView, "scaleY", 1f, 2f)
        val alpha1  = ObjectAnimator.ofFloat(mBinding.rippleView, "alpha", 1f, 0f)

        val scaleX2 = ObjectAnimator.ofFloat(mBinding.rippleView, "scaleX", 1f, 3f)
        val scaleY2 = ObjectAnimator.ofFloat(mBinding.rippleView, "scaleY", 1f, 3f)
        val alpha2  = ObjectAnimator.ofFloat(mBinding.rippleView, "alpha", 1f, 0f)

        val scaleX3 = ObjectAnimator.ofFloat(mBinding.rippleView, "scaleX", 1f, 4f)
        val scaleY3 = ObjectAnimator.ofFloat(mBinding.rippleView, "scaleY", 1f, 4f)
        val alpha3  = ObjectAnimator.ofFloat(mBinding.rippleView, "alpha", 1f, 0f)

        scaleX1.setDuration(500);
        scaleY1.setDuration(500);
        alpha1.setDuration(500);

        scaleX2.setDuration(700);
        scaleY2.setDuration(700);
        alpha2.setDuration(700);

        scaleX3.setDuration(900);
        scaleY3.setDuration(900);
        alpha3.setDuration(900);

        scaleX1.start();
        scaleY1.start();
        alpha1.start();

        scaleX2.start();
        scaleY2.start();
        alpha2.start();

        scaleX3.start();
        scaleY3.start();
        alpha3.start();
    }

    override fun initDialogStyle(window: Window) {
        super.initDialogStyle(window)
        window.setGravity(Gravity.BOTTOM)
    }
}