package com.feb.module_home.mine

import android.graphics.Color
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.module_home.R
import com.feb.module_home.databinding.MineVipActivityBinding

class MineVipPageActivity : BaseMVVMActivity<MineVipActivityBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.mine_vip_activity
    }

    override fun initView() {
        mBinding.customTopBar.apply {
            setColor(Color.WHITE)
        }
    }

    override fun initData() {

    }
}