package com.feb.module_home

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.databinding.FragmentFindBinding
import com.feb.module_home.find.adapter.FindDynamicAdapter

@Route(path = ARouterConstants.HOME_FIND)
class FindFragment : BaseMVVMFragment<FragmentFindBinding>() {

    val adapter = FindDynamicAdapter()
    override fun getLayoutId(): Int {
        return R.layout.fragment_find
    }

    override fun initView() {
        mBinding.findRvList.adapter = adapter
        mBinding.findRvList.layoutManager = LinearLayoutManager(activity)

    }

    override fun initData() {
        val data = mutableListOf<String>()
        for (i in 0 until 20) {
            data.add("${i}")
        }
        adapter.setNewData(data)
        mBinding.ivSendDynamic.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.FIND_POST_UPDATES).navigation()
        }
    }

    override fun initListener() {
        super.initListener()
        adapter.setOnItemChildClickListener { adapter, view, position ->
            when(view.id) {
                R.id.iv_promulgator_avatar ->{
                    ARouter.getInstance().build(ARouterConstants.HOME_DETAIL).navigation()
                }
                R.id.iv_call_answer -> {
                    ARouter.getInstance().build(ARouterConstants.MAKE_AND_RECEIVE_CALL).navigation()
                }
                R.id.iv_like -> {

                }
            }

        }
    }
}