package com.feb.module_home.mine.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.module_home.R
import com.feb.module_home.databinding.MineFollowFragmentBinding
import com.feb.module_home.mine.adapter.MineFollowAdapter
import com.feb.module_home.msg.ui.MessageFragment

class MineFollowFragment : BaseMVVMFragment<MineFollowFragmentBinding>() {

    val adapter = MineFollowAdapter()
    override fun getLayoutId(): Int {
        return R.layout.mine_follow_fragment
    }

    override fun initView() {
        mBinding.followListRv.layoutManager = LinearLayoutManager(activity)
        mBinding.followListRv.adapter = adapter
    }

    override fun initData() {
        val data = mutableListOf<String>()
        for (i in 0 until 20) {
            data.add("${i}")
        }
        adapter.setNewData(data)
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Int) = MineFollowFragment().apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }
}