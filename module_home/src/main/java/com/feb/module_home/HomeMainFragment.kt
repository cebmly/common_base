package com.feb.module_home

import android.graphics.Typeface
import android.view.Gravity
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.feb.lib_common.ResourceUtil
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.databinding.FragmentHomeMainBinding
import com.feb.module_home.follow.FollowFragment

@Route(path = ARouterConstants.INDEX_HOME)
class HomeMainFragment : BaseMVVMFragment<FragmentHomeMainBinding>() {

    private val mTabs = arrayOf("Follow", "Popular","Game", "dynamic")
    override fun getLayoutId(): Int {
        return R.layout.fragment_home_main
    }

    override fun initView() {

    }

    override fun initData() {

        mBinding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = mTabs.size

            override fun createFragment(position: Int): Fragment {
                return FollowFragment.newInstance(position)
            }
        }
        //防止回收fragment
        ViewPager2Delegate.install(mBinding.viewPager, mBinding.tablayout)
        mTabs.forEach {
            mBinding.tablayout.addView(TextView(context).apply {
                text = it
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
                setPadding(0, 0, ResourceUtil.getDimen(14f), 0)
            })
        }
        mBinding.viewPager.setCurrentItem(0, false)

    }

}