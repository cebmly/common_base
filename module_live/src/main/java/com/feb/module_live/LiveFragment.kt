package com.feb.module_live

import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_live.databinding.LiveFragmentLayout2Binding

@Route(path = ARouterConstants.LIVE_INDEX)
class LiveFragment : BaseMVVMFragment<LiveFragmentLayout2Binding>() {
    override fun getLayoutId(): Int {
        return R.layout.live_fragment_layout2
    }

    override fun initView() {

    }

    override fun initData() {

    }
}