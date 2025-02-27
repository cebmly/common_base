package com.feb.module_home.match

import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.R
import com.feb.module_home.databinding.MatchCallIngActivityBinding

@Route(path = ARouterConstants.MATCH_SCAN_CALL_ING)
class MatchScanCallActivity : BaseMVVMActivity<MatchCallIngActivityBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.match_call_ing_activity
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        super.initListener()
        mBinding.tvStopMatch.setOnClickListener {
            finish()
        }
    }
}