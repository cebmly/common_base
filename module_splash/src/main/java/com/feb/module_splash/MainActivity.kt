package com.feb.module_splash

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.FragmentUtils
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.lib_common.expand.toGone
import com.feb.lib_common.expand.toVisible
import com.feb.module_splash.databinding.ActivityMain2Binding

@Route(path = ARouterConstants.MAIN)
class MainActivity : BaseMVVMActivity<ActivityMain2Binding>() {


    var selectPos = 0

    private var fragments: Array<Fragment>? = arrayOf(
        ARouter.getInstance().build(ARouterConstants.INDEX_HOME).navigation() as Fragment,
        ARouter.getInstance().build(ARouterConstants.HOME_MATCH).navigation() as Fragment,
        ARouter.getInstance().build(ARouterConstants.HOME_FIND).navigation() as Fragment,
        ARouter.getInstance().build(ARouterConstants.HOME_CHAT).navigation() as Fragment,
        ARouter.getInstance().build(ARouterConstants.HOME_MINE).navigation() as Fragment,
    )



    companion object {
        const val TAB_INDEX = 0
        const val TAB_DISCOVER = 1
        const val TAB_CHAT = 2
        const val TAB_NEWS = 3
        const val TAB_ME = 4
    }
    private val mTabs = arrayOf("人气", "巨星")

    override fun getLayoutId(): Int {
        return R.layout.activity_main2
    }

    override fun initView() {
        mBinding.rlIndex.setOnClickListener { view: View? -> onClick(view)  }
        mBinding.rlDiscover.setOnClickListener { view: View? -> onClick(view)  }
        mBinding.rlLive.setOnClickListener { view: View? -> onClick(view)  }
        mBinding.rlMsg.setOnClickListener { view: View? -> onClick(view)  }
        mBinding.rlMe.setOnClickListener { view: View? -> onClick(view)  }
    }

    override fun initData() {

        fragments?.let { FragmentUtils.add(supportFragmentManager, it, R.id.container, selectPos) }
        selectShow(selectPos)
    }
    private fun onClick(view: View?) {

        var positon = TAB_INDEX
        positon = when (view?.id) {
            R.id.rl_live -> TAB_CHAT
            R.id.rl_discover -> TAB_DISCOVER
            R.id.rl_msg -> TAB_NEWS
            R.id.rl_me -> TAB_ME
            else -> TAB_INDEX
        }
        selectShow(positon)
    }
    private fun selectShow(position: Int) {

        reset()

        this.selectPos = position
        var name = ""
        when (position) {
            TAB_NEWS -> {
                mBinding.ivNews.toGone()
                mBinding.ivNews2.toVisible()
                mBinding.ivChat2.toGone()
            }

            TAB_INDEX -> {

                mBinding.ivIndex.toGone()
                mBinding.ivIndex2.toVisible()
                mBinding.ivChat2.toGone()
            }

            TAB_ME -> {

                mBinding.ivMe.toGone()
                mBinding.ivMe2.toVisible()
                mBinding.ivChat2.toGone()
            }

            TAB_CHAT -> {
                mBinding.ivChat.toGone()
                mBinding.ivChat2.toVisible()
            }

            TAB_DISCOVER -> {
                mBinding.ivDiscover.toGone()
                mBinding.ivDiscover2.toVisible()
                mBinding.ivChat2.toGone()
            }
        }
        isLightMode = true
        fragments?.let { FragmentUtils.showHide(selectPos, *it) }
    }

    private fun reset() {

        mBinding.ivNews.toVisible()
        mBinding.ivIndex.toVisible()
        mBinding.ivDiscover.toVisible()
        mBinding.ivMe.toVisible()
        mBinding.ivChat.toVisible()
        mBinding.ivNews2.toGone()
        mBinding.ivIndex2.toGone()
        mBinding.ivDiscover2.toGone()
        mBinding.ivMe2.toGone()
        mBinding.ivChat2.toGone()
    }
}