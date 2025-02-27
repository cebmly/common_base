package com.feb.lib_utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.MetaDataUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;

import java.net.URLEncoder;

public class UtilConfig {
    private static Context context;
    public static String emulator = "0";
    public static String salt = "";
    public static String oaid;
    public static String channelId = "default";
    public static String userAgent = "Android";
    public static String macAddress = "";
    public static String imei = "";
    public static String androidId = "";
    public static String deviceModel = "";
    public static String deviceId = "";

    public static void init(Context context) {
        setContext(context);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        UtilConfig.context = context;

        try {
            channelId = MetaDataUtils.getMetaDataInApp("TD_CHANNEL_ID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initUserAgent();
    }

    public static void initImei() {
        try {
            if (!TextUtils.isEmpty(androidId)) {
                androidId = DeviceUtils.getAndroidID();
            }
            checkInEmulator();
            imei = PhoneUtils.getIMEI();
            deviceModel = DeviceUtils.getModel();
            com.blankj.utilcode.util.ThreadUtils.executeByCached(new ThreadUtils.SimpleTask<Object>() {
                @Override
                public Object doInBackground() throws Throwable {
                    return macAddress = NetUtils.getMac(Utils.getApp());
                }

                @Override
                public void onSuccess(Object result) {
                    LoggerUtils.d("获取mac地址成功", result);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改了okhttp请求头和标准请求不一致的错误
    private static void initUserAgent() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    userAgent = WebSettings.getDefaultUserAgent(context);
                } catch (Exception e) {
                    userAgent = System.getProperty("http.agent");
                }
            } else {
                userAgent = System.getProperty("http.agent");
            }
            userAgent = System.getProperty("http.agent");
            //调整编码，防止中文出错
            userAgent = URLEncoder.encode(userAgent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkInEmulator() {
//        emulator = EasyProtectorLib.checkIsRunningInEmulator(getContext(), new EmulatorCheckCallback() {
//            @Override
//            public void findEmulator(String emulatorInfo) {
////                LoggerUtils.d(emulatorInfo);
//            }
//        }) ? "1" : "0";
    }

    public static String getSalt() {
        return salt;
    }

    public static void setSalt(String salt) {
        UtilConfig.salt = salt;
    }
}
