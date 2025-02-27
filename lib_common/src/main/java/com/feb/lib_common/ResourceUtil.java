package com.feb.lib_common;

import com.blankj.utilcode.util.Utils;
import com.feb.lib_common.base.BaseApplication;

import me.jessyan.autosize.utils.AutoSizeUtils;

public class ResourceUtil {
    public static int getDimen(float dimen) {
        return AutoSizeUtils.dp2px(BaseApplication.getInstance(), dimen);
    }

    public static int getSp(float dimen) {
        return AutoSizeUtils.sp2px(BaseApplication.getInstance(), dimen);
    }

    public static int getColor(int color) {
        return Utils.getApp().getResources().getColor(color);
    }
}
