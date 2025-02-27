package com.feb.module_dynamic

import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_dynamic.databinding.DynamicActivityBinding

@Route(path = ARouterConstants.DYNAMIC_DETAIL)
class DynamicActivity : BaseMVVMActivity<DynamicActivityBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.dynamic_activity
    }

    override fun initView() {

    }

    override fun initData() {

    }
}