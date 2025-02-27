package com.feb.module_home.follow

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.module_home.R
import com.feb.module_home.databinding.FragmentFollowBinding
import com.feb.module_home.follow.adapter.RoomListAllAdapter
import com.luck.picture.lib.utils.ToastUtils

class FollowFragment : BaseMVVMFragment<FragmentFollowBinding>() {

    var listRoom = ArrayList<Int>()
    var adapter = RoomListAllAdapter()
    override fun getLayoutId(): Int {
        return R.layout.fragment_follow
    }

    override fun initView() {

        mBinding.homeRvListRoom.layoutManager = GridLayoutManager(activity,2)
        mBinding.homeRvListRoom.adapter = adapter


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
        fun newInstance(type: Int) = FollowFragment().apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }
}