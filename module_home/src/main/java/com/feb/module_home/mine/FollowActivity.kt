package com.feb.module_home.mine

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.feb.lib_common.ResourceUtil
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_home.R
import com.feb.module_home.databinding.MineFollowActivityBinding
import com.feb.module_home.mine.fragment.MineFollowFragment

@Route(path = ARouterConstants.HOME_PAGE_FOLLOW)
class FollowActivity : BaseMVVMActivity<MineFollowActivityBinding>() {

    private val mTabs = arrayOf("Follow ", "Followers")
    override fun getLayoutId(): Int {
        return R.layout.mine_follow_activity
    }

    override fun initView() {
        mBinding.customTopBar.apply {
            setColor(Color.WHITE)
        }
    }

    override fun initData() {
        mBinding.vp.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = mTabs.size

            override fun createFragment(position: Int): Fragment {
                return MineFollowFragment.newInstance(position)
            }
        }
        ViewPager2Delegate.install(mBinding.vp, mBinding.tablayout)
        mTabs.forEach {
            mBinding.tablayout.addView(TextView(this).apply {
                text = it
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
                setPadding(0, 0, ResourceUtil.getDimen(24f), 0)
            })
        }
        mBinding.vp.setCurrentItem(0, false)
    }
}