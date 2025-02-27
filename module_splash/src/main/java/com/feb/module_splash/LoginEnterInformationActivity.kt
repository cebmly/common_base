package com.feb.module_splash

import android.content.Intent
import android.graphics.Color
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.feb.lib_common.GlideEngine
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.module_splash.databinding.ActivityLoginEnterInformationBinding
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.utils.MediaUtils
import com.luck.picture.lib.utils.ToastUtils


@Route(path = ARouterConstants.SPLASH_MAIN)
class LoginEnterInformationActivity : BaseMVVMActivity<ActivityLoginEnterInformationBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login_enter_information
    }

    override fun initView() {
        mBinding.topBar.apply {
            setTitle("Fill your information")
            setColor(Color.WHITE)
        }
    }

    override fun initData() {

    }

    override fun initListener() {
        super.initListener()
        with(mBinding){
            ivAvatar.setOnClickListener { openCamera() }
            tvSubmit.setOnClickListener {
                ToastUtils.showToast(this@LoginEnterInformationActivity,"跳转")
//                Log.d("ARouterDebug", "Attempting to navigate to MainActivity")
//                ARouter.getInstance().build("/module_splash/MainActivity").navigation()
                startActivity(Intent(this@LoginEnterInformationActivity,MainActivity::class.java))
            }
        }
    }

    private fun openCamera() {
        PictureSelector.create(this)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine.createGlideEngine())
            .setMaxSelectNum(1)
            .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
//            if (requestCode == PictureConfig.CHOOSE_REQUEST || requestCode == PictureConfig.REQUEST_CAMERA) {
//                val result = PictureSelector.obtainSelectorList(intent)
//                analyticalSelectResults(result)
//            }
            val albumMedia = PictureSelector.obtainSelectorList(data)
            if (albumMedia != null && albumMedia.size != 0) {
                val imgMedia = albumMedia[0]
                //本地url
                val mediaUrl: String = if (imgMedia.isCompressed) {
                    imgMedia.compressPath
                } else {
                    imgMedia.realPath
                }
                Glide.with(this).load(mediaUrl).into(mBinding.ivAvatar)
            }
        }
    }

    private fun analyticalSelectResults(result: ArrayList<LocalMedia>) {
        for (media in result) {
            if (media.width == 0 || media.height == 0) {
                if (PictureMimeType.isHasBmp(media.mimeType)) {
                    val imageExtraInfo = MediaUtils.getImageSize(this, media.path)
                    media.width = imageExtraInfo.width
                    media.height = imageExtraInfo.height
                } else if (PictureMimeType.isHasVideo(media.mimeType)) {
                    val videoExtraInfo = MediaUtils.getVideoSize(this, media.path)
                    media.width = videoExtraInfo.width
                    media.height = videoExtraInfo.height
                }
            }
            Glide.with(this).load(media.realPath).into(mBinding.ivAvatar)


        }
    }
}