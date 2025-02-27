package com.feb.module_dynamic

import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_dynamic.databinding.DynamicFragmentLayoutBinding
import com.luck.picture.lib.utils.ToastUtils

@Route(path = ARouterConstants.DYNAMIC_INDEX)
class DynamicFragment : BaseMVVMFragment<DynamicFragmentLayoutBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.dynamic_fragment_layout
    }

    override fun initView() {
        ToastUtils.showToast(activity,"2")
    }

    override fun initData() {

    }
}