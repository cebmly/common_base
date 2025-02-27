package com.feb.module_mine

import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_mine.databinding.MineFragmentLayoutBinding

@Route(path = ARouterConstants.MINE_INDEX)
class MineFragment : BaseMVVMFragment<MineFragmentLayoutBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.mine_fragment_layout
    }

    override fun initView() {

    }

    override fun initData() {

    }
}