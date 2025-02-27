package com.feb.module_splash

import android.content.Intent
import android.os.Build
import com.blankj.utilcode.util.DeviceUtils
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_utils.LoggerUtils
import com.feb.lib_utils.UtilConfig
import com.feb.module_splash.databinding.LoginSplashActivityBinding
import com.luck.picture.lib.utils.ToastUtils

class LoginSplashActivity : BaseMVVMActivity<LoginSplashActivityBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.login_splash_activity
    }

    override fun initView() {
//        "28b8f02de6c3735c5a734af1325939508"
        ToastUtils.showToast(this,DeviceUtils.getUniqueDeviceId())
        val uniqueDeviceId = DeviceUtils.getAndroidID()
        val brand = Build.BRAND
        val model = Build.MODEL
        LoggerUtils.d(DeviceUtils.getUniqueDeviceId())
    }

    override fun initData() {
        mBinding.ivLoginPhone.setOnClickListener {
            startActivity(Intent(this,LoginEnterInformationActivity::class.java))
        }
    }
    
}