package com.feb.module_dynamic

import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_dynamic.databinding.DynamicFragmentLayoutBinding

@Route(path = ARouterConstants.DISCOVER_INDEX)
class DiscoverFragment : BaseMVVMFragment<DynamicFragmentLayoutBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.dynamic_fragment_layout
    }

    override fun initView() {

    }

    override fun initData() {

    }
}