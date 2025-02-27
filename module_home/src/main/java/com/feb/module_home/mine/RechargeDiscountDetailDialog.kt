package com.feb.module_home.mine

import android.os.Build
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMvvmDialogFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.R
import com.feb.module_home.databinding.DialogRechargeCurrencyBinding
import com.feb.module_home.mine.adapter.RechargeDiscountDetailAdapter

@Route(path = ARouterConstants.RECHARGE_DISCOUNT_DETAIL)
class RechargeDiscountDetailDialog : BaseMvvmDialogFragment<DialogRechargeCurrencyBinding>() {

    val mRechargeAdapter = RechargeDiscountDetailAdapter()
    override fun getLayoutId(): Int {
        return R.layout.dialog_recharge_currency
    }

    override fun initView() {
        mBinding.rvRechargeList.layoutManager = LinearLayoutManager(activity)
        mBinding.rvRechargeList.adapter = mRechargeAdapter
    }

    override fun initData() {
        val data = mutableListOf<String>()
        for (i in 0 until 20) {
            data.add("${i}")
        }
        mRechargeAdapter.setNewData(data)
    }

    override fun initDialogStyle(window: Window) {
        super.initDialogStyle(window)
        window.setGravity(Gravity.BOTTOM)
    }
}