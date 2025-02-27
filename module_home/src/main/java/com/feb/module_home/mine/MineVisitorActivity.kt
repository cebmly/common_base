package com.feb.module_home.mine

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.lib_common.expand.toGone
import com.feb.module_home.R
import com.feb.module_home.databinding.MineVisitorActivityBinding
import com.feb.module_home.mine.adapter.MineVisitorAdapter

@Route(path = ARouterConstants.HOME_PAGE_VISITOR)
class MineVisitorActivity : BaseMVVMActivity<MineVisitorActivityBinding>() {

    val adapter = MineVisitorAdapter()

    override fun getLayoutId(): Int {
        return R.layout.mine_visitor_activity
    }

    override fun initView() {
        mBinding.customTopBar.apply {
            setColor(Color.WHITE)
            setTitle("Visitor")
        }
        mBinding.rvVisitor.layoutManager = LinearLayoutManager(this)
        mBinding.rvVisitor.adapter = adapter
    }

    override fun initData() {
        val data = mutableListOf<String>()
        for (i in 0 until 20) {
            data.add("${i}")
        }
        adapter.setNewData(data)
    }

    override fun initListener() {
        super.initListener()
        mBinding.tvUnLock.setOnClickListener {
            mBinding.clLockCover.toGone()
        }
    }
}