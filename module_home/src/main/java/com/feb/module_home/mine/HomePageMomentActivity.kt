package com.feb.module_home.mine

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.R
import com.feb.module_home.databinding.MineMomentActivityBinding
import com.feb.module_home.mine.adapter.MineMomentAdapter

@Route(path = ARouterConstants.HOME_PAGE_MOMENT)
class HomePageMomentActivity : BaseMVVMActivity<MineMomentActivityBinding>() {

    val momentAdapter = MineMomentAdapter()
    override fun getLayoutId(): Int {
        return R.layout.mine_moment_activity
    }

    override fun initView() {
        mBinding.customTopBar.apply {
            setColor(Color.WHITE)
            setTitle("Moment")
        }

        mBinding.rvMineMoment.layoutManager = LinearLayoutManager(this)
        mBinding.rvMineMoment.adapter = momentAdapter
    }

    override fun initData() {
        val data = mutableListOf<String>()
        for (i in 0 until 20) {
            data.add("${i}")
        }
        momentAdapter.setNewData(data)
    }
}