package com.feb.net

import android.os.Build
import com.blankj.utilcode.BuildConfig
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.LanguageUtils
import com.blankj.utilcode.util.StringUtils
import java.net.URLEncoder
import java.util.Locale

class DeviceIdUtils {

    companion object {
        // 缓存 Android ID
        private var cachedAndroidId: String? = null
        private var cachedDeviceId: String? = null
        private var testStringSrc: String = BuildConfig.APPLICATION_ID_Device_Test

        //                public val APPLICATION_ID = "app.duomevideochat.duome.alpha"
        public val APPLICATION_ID = BuildConfig.APPLICATION_ID_Device

        /**
         * api请求头Payload参数数据
         * */
        fun getPayload(): String {
            return String.format(
                "deviceId=%s&channel=%s&appVersion=%s" +
                        "&platform=1&bundleId=%s&osVersion=%d&lang=%s&region=%s&locale=%s",
                URLEncoder.encode(getDeviceId(), "UTF-8"),
                URLEncoder.encode(channelName, "UTF-8"),
                versionName,
                APPLICATION_ID,
                osVersion,
                appLocal.language,
                appLocal.isO3Country,
                appLocal.language
            )
        }

        fun getDeviceId(): String? {
            if (StringUtils.isEmpty(cachedDeviceId)) {
                if (cachedDeviceId == null) {
                    cachedDeviceId = DeviceUtils.getAndroidID()
                }
                if (cachedDeviceId == null || cachedDeviceId?.length == 0) {
                    cachedDeviceId = Build.BRAND.replace(" ", "") +
                            EncryptUtils.encryptMD2ToString(System.getProperty("http.agent") + System.currentTimeMillis())
                } else {
                    cachedDeviceId = Build.BRAND.replace(" ", "") + cachedDeviceId
                }
                if (cachedDeviceId!!.length > 50) {
                    cachedDeviceId = cachedDeviceId!!.substring(0, 50)
                }
            }
            return cachedDeviceId+testStringSrc
        }

        fun getAndroidID(): String? {
            if (StringUtils.isEmpty(cachedDeviceId)) {
                if (cachedAndroidId == null) {
                    cachedAndroidId = DeviceUtils.getAndroidID()
                }
            }
            return cachedAndroidId+testStringSrc
        }

        fun getImei(): String {
            return IMEIUtil.iMEI
        }

        val platform = "1"

        /**
         * 渠道名称
         * */
        val channelName: String? = "android_default"

        /**
         * 版本名称
         */
        val versionName: String? = AppUtils.getAppVersionName()

        /**
         * 系统版本
         * */
        val osVersion: Int = Build.VERSION.SDK_INT

        /**
         * 系统语言
         * */
        val systemLocal: Locale = LanguageUtils.getSystemLanguage()

        /**
         * app语言
         * */
        var appLocal: Locale =
            MultiLanguages.getAppLanguage(AppApplication.getInstance()) ?: systemLocal

    }
}