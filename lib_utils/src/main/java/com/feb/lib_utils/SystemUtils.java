package com.feb.lib_utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.orhanobut.logger.BuildConfig;

import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class SystemUtils {

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    public static String getClientID(String channel) {
        StringBuffer sb = new StringBuffer();
        sb.append(channel);
        sb.append(Build.SERIAL);
        sb.append(Build.BOARD.length() % 10);
        sb.append(Build.BRAND.length() % 10);
        sb.append(Build.CPU_ABI.length() % 10);
        sb.append(Build.DEVICE.length() % 10);
        sb.append(Build.DISPLAY.length() % 10);
        sb.append(Build.HOST.length() % 10);
        sb.append(Build.ID.length() % 10);
        sb.append(Build.MANUFACTURER.length() % 10);
        sb.append(Build.MODEL.length() % 10);
        sb.append(Build.PRODUCT.length() % 10);
        sb.append(Build.TAGS.length() % 10);
        sb.append(Build.TYPE.length() % 10);
        sb.append(Build.USER.length() % 10);

        return Md5Utils.get(sb.toString());
    }


    public static String getShortClientID(Context context, String channel) {
        String cid = getClientID(channel);
        return Md5Utils.get(cid + getUUID(context));
    }

    public static String getShortClientID(Context context) {
        String cid = getClientID("xqipaoandroid");
        return Md5Utils.get(cid + getUUID(context));
    }

    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        String system_uuid_key = "system_uuid";
        ConfigUtils configUtils = ConfigUtils.getInstance(context);
        configUtils.setConfigName(system_uuid_key);
        String system_config_uuid = configUtils.findStringByKey(system_uuid_key);
        if (system_config_uuid == null) {
            system_config_uuid = UUID.randomUUID().toString();
            configUtils.addOrUpdateText(system_uuid_key, system_config_uuid);
        }
        return system_config_uuid;
    }

    /**
     * 系统参数
     *
     * @return
     */
    public static Map<String, String> getSystemParams() {
        NullHashMap<String, String> headers = new NullHashMap<>();
        headers.put("deviceId", UtilConfig.deviceId);
        headers.put("appVersion", BuildConfig.VERSION_NAME);
        headers.put("versionName", BuildConfig.VERSION_NAME);
        headers.put("versionCode", String.valueOf(BuildConfig.VERSION_CODE));
        headers.put("clientType", "android");
        headers.put("emulator", UtilConfig.emulator);
//        headers.put("did", AppLog.getDid());
        headers.put("deviceName", URLEncoder.encode(SystemUtils.getDeviceBrand() + SystemUtils.getSystemModel() + SystemUtils.getSystemVersion()));
        headers.put("VESTNAME", "douwan");
        try {
            headers.put("Channel-Code", UtilConfig.channelId);
            headers.put("CHANNELID", UtilConfig.channelId);
            //AndroidId
            headers.put("ANDROIDID", UtilConfig.androidId);
            headers.put("appName", URLEncoder.encode(AppUtils.getAppName()));
            headers.put("osVer", SystemUtils.getSystemVersion());
            if (StaticVariablesKtKt.isAgreePolicy()) {
                if (NetUtils.getIP() != null) {
                    headers.put("ip", NetUtils.getIP().toString());
                } else {
                    headers.put("ip", "");
                }
            } else {
                headers.put("ip", "");
            }

            //OAID
            if (UtilConfig.oaid != null) {
                headers.put("oaid", UtilConfig.oaid);
            }

            //IMEI
            headers.put("imei", UtilConfig.imei);
            //0 Android 1 IOS
            headers.put("os", "0");
            //手机型号
            if (!TextUtils.isEmpty(UtilConfig.deviceModel)) {
                headers.put("model", URLEncoder.encode(UtilConfig.deviceModel));
            }
            //ua
            headers.put("ua", UtilConfig.userAgent);
            //mac
            headers.put("mac", UtilConfig.macAddress);
            //end 推广需要加的参数
        } catch (Exception e) {
            e.printStackTrace();
        }
        return headers;
    }
}
