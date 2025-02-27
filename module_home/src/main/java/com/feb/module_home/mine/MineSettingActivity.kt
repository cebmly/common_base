package com.feb.module_home.mine

import android.graphics.Color
import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.R
import com.feb.module_home.databinding.MineSettingActivityBinding

@Route(path = ARouterConstants.MINE_SETTING_PAGE)
class MineSettingActivity : BaseMVVMActivity<MineSettingActivityBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.mine_setting_activity
    }

    override fun initView() {
        mBinding.customTopBar.apply {
            setColor(Color.WHITE)
            setTitle("Settings")
        }
    }

    override fun initData() {

    }
}