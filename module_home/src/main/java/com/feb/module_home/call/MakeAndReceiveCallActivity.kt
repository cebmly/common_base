package com.feb.module_home.call

import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.R
import com.feb.module_home.databinding.ActivityMakeAndReceiveCallsBinding

@Route(path = ARouterConstants.MAKE_AND_RECEIVE_CALL)
class MakeAndReceiveCallActivity : BaseMVVMActivity<ActivityMakeAndReceiveCallsBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_make_and_receive_calls
    }

    override fun initView() {
        mBinding.llHangUp.setOnClickListener {
            finish()
        }
    }

    override fun initData() {

    }
}