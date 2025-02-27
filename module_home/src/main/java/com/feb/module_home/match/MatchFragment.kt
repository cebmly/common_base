package com.feb.module_home.match

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.lib_utils.DialogUtils
import com.feb.module_home.R
import com.feb.module_home.databinding.MatchFragmentPageBinding

@Route(path = ARouterConstants.HOME_MATCH)
class MatchFragment : BaseMVVMFragment<MatchFragmentPageBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.match_fragment_page
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        super.initListener()
        mBinding.llStartScan.setOnClickListener {
            DialogUtils.showDialogFragment(ARouter.getInstance().build(ARouterConstants.MATCH_SCAN_DIALOG).navigation())
        }
    }
}