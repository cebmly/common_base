package com.feb.module_home

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.databinding.ActivityPersonalHomepageBinding
import com.feb.module_home.mine.adapter.HomePageMomentListAdapter
import com.feb.module_home.mine.adapter.MinePhotoListAdapter

@Route(path = ARouterConstants.HOME_DETAIL)
class HomeDetailActivity : BaseMVVMActivity<ActivityPersonalHomepageBinding>() {

    val mMomentAdapter = HomePageMomentListAdapter()
    val mPhotoAdapter = MinePhotoListAdapter()
    override fun getLayoutId(): Int {
        return R.layout.activity_personal_homepage
    }

    override fun initView() {
        mBinding.customTopBar.apply {
            setColor(Color.WHITE)
            setRightIcon(com.feb.lib_common.R.mipmap.common_ic_control_more)
        }
        mBinding.rvMoment.adapter = mMomentAdapter
        mBinding.rvMoment.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mBinding.rvPhoto.adapter = mPhotoAdapter
        mBinding.rvPhoto.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    override fun initData() {
        val data = mutableListOf<String>()
        for (i in 0 until 10) {
            data.add("${i}")
        }
        mMomentAdapter.setNewData(data)
        mPhotoAdapter.setNewData(data)
    }

    override fun initListener() {
        super.initListener()
        mBinding.tvMomentsMore.setOnClickListener {
            ARouter.getInstance().build(ARouterConstants.HOME_PAGE_MOMENT).navigation()
        }
    }
}