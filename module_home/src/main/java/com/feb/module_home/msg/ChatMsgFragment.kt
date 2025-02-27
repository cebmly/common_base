package com.feb.module_home.msg

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
import com.feb.module_home.R
import com.feb.module_home.databinding.ChatMsgFragmentBinding
import com.feb.module_home.msg.ui.MessageFragment

@Route(path = ARouterConstants.HOME_CHAT)
class ChatMsgFragment : BaseMVVMFragment<ChatMsgFragmentBinding>() {

    private val mTabs = arrayOf("Messages ", "Follow")
    override fun getLayoutId(): Int {
        return R.layout.chat_msg_fragment
    }

    override fun initView() {

    }

    override fun initData() {
        mBinding.vp.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = mTabs.size

            override fun createFragment(position: Int): Fragment {
                return MessageFragment.newInstance(position)
            }
        }
        //防止回收fragment
        ViewPager2Delegate.install(mBinding.vp, mBinding.tablayout)
        mTabs.forEach {
            mBinding.tablayout.addView(TextView(context).apply {
                text = it
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
                setPadding(0, 0, ResourceUtil.getDimen(14f), 0)
            })
        }
        mBinding.vp.setCurrentItem(0, false)
    }
}