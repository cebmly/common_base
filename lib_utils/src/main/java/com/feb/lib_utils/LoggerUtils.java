package com.feb.lib_utils;

import android.text.TextUtils;

import com.blankj.utilcode.BuildConfig;
import com.blankj.utilcode.util.ObjectUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoggerUtils {
    private static String isGoodJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                return jsonObject.toString(2);
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                return jsonArray.toString(2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    //强转string 输出
    private static String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (!object.getClass().isArray()) {
            return object.toString();
        }
        if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        }
        if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        }
        if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        }
        if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        }
        if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        }
        if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        }
        if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        }
        if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        }
        if (object instanceof Object[]) {
            return Arrays.deepToString((Object[]) object);
        }
        return "Couldn't find a correct type for the object";
    }


    public static void d(String tag, Object obj) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            //前面已经判断过  如果空不会走到这里   这里返回null只表示不是json
            String msg = isGoodJson(content);
            if (msg == null) {
                Logger.t(tag).d(content);
            } else {
                Logger.t(tag).d("copy json" + "\r\n" + content + "\r\n\r" + "format json:\r\n" + msg);
            }
        } else {
            Logger.t(tag).d(obj);
        }
    }

    public static void d(Object obj) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            String msg = isGoodJson(content);
            if (msg == null) {
                Logger.d(content);
            } else {
                Logger.d("copy json" + "\r\n" + content + "\r\n\r" + "format json:\r\n" + msg);
            }
        } else {
            Logger.d(obj);
        }
    }


    public static void e(String tag, Object obj) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            String msg = isGoodJson(content);
            //前面已经判断过  如果空不会走到这里   这里返回null只表示不是json
            if (msg == null) {
                Logger.t(tag).e(content);
            } else {
                Logger.t(tag).e("copy json" + "\r\n" + content + "\r\n\r" + "format json:\r\n" + msg);
            }
        } else {
            Logger.t(tag).e(toString(obj));
        }
    }

    public static void e(Object obj) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            String msg = isGoodJson(content);
            //前面已经判断过  如果空不会走到这里   这里返回null只表示不是json
            if (msg == null) {
                Logger.e(content);
            } else {
                Logger.e("copy json" + "\r\n" + content + "\r\n\r" + "format json:\r\n" + msg);
            }
        } else {
            Logger.e(toString(obj));
        }
    }


    public static void w(String tag, Object obj) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            String msg = isGoodJson(content);
            if (msg == null) {
                Logger.t(tag).w(content);
            } else {
                Logger.t(tag).w("copy json" + "\r\n" + content + "\r\n\r" + "format json:\r\n" + msg);
            }
        } else {
            Logger.w(toString(obj));
        }
    }

    public static void w(Object obj) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            String msg = isGoodJson(content);
            if (msg == null) {
                Logger.t(null).w(content);
            } else {
                Logger.w("copy json" + "\r\n" + content + "\r\n\r" + "format json:\r\n" + msg);
            }
        } else {
            Logger.w(toString(obj));
        }
    }


    public static void i(String tag, Object obj) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            String msg = isGoodJson(content);
            if (msg == null) {
                Logger.t(tag).i(content);
            } else {
                Logger.t(tag).i("copy json" + "\r\n" + content + "\r\n\r" + "format json:\r\n" + msg);
            }
        } else {
            Logger.t(tag).i(toString(obj));
        }
        ;
    }

    public static void i(Object obj) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            String msg = isGoodJson(content);
            if (msg == null) {
                Logger.t(null).i(content);
            } else {
                Logger.i("copy json" + "\r\n" + content + "\r\n\r" + "format json:\r\n" + msg);
            }
        } else {
            Logger.i(toString(obj));
        }
    }

}
