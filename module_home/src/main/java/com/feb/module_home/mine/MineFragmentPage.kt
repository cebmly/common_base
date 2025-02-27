package com.feb.module_home.mine

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.lib_utils.DialogUtils
import com.feb.module_home.R
import com.feb.module_home.databinding.MineFragmentPageBinding

@Route(path = ARouterConstants.HOME_MINE)
class MineFragmentPage : BaseMVVMFragment<MineFragmentPageBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.mine_fragment_page
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        super.initListener()
        mBinding.ivUserAvatar.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.HOME_DETAIL).navigation()
        }
        mBinding.llFollow.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.HOME_PAGE_FOLLOW).navigation()
        }
        mBinding.llFollowing.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.HOME_PAGE_FOLLOW).navigation()
        }
        mBinding.llMoments.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.HOME_PAGE_MOMENT).navigation()
        }
        mBinding.llVisitor.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.HOME_PAGE_VISITOR).navigation()
        }
        mBinding.clSetting.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.MINE_SETTING_PAGE).navigation()
        }
        mBinding.ivSetting.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.MINE_SETTING_PAGE).navigation()
        }
        mBinding.clBlackList.setOnClickListener {
            startActivity(Intent(activity,MineBlackListActivity::class.java))
        }
        mBinding.llVip.setOnClickListener {
            startActivity(Intent(activity,MineVipPageActivity::class.java))
        }

        mBinding.cvCoins.setOnClickListener {
            DialogUtils.showDialogFragment(
                ARouter.getInstance().build(ARouterConstants.RECHARGE_DISCOUNT_DETAIL).navigation()
            )
        }
    }
}