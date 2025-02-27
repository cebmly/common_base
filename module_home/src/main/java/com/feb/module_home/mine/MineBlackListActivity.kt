package com.feb.module_home.mine

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.module_home.R
import com.feb.module_home.databinding.MineBlackListActivityBinding
import com.feb.module_home.mine.adapter.MineBlackListAdapter

class MineBlackListActivity : BaseMVVMActivity<MineBlackListActivityBinding>() {

    val adapter = MineBlackListAdapter()
    override fun getLayoutId(): Int {
        return R.layout.mine_black_list_activity
    }

    override fun initView() {
        mBinding.customTopBar.apply {
            setColor(Color.WHITE)
            setTitle("Black list")
        }
        mBinding.rvBlackList.layoutManager = LinearLayoutManager(this)
        mBinding.rvBlackList.adapter = adapter
    }

    override fun initData() {
        val data = mutableListOf<String>()
        for (i in 0 until 20) {
            data.add("${i}")
        }
        adapter.setNewData(data)
    }
}