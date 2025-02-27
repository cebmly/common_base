package com.feb.lib_utils;

import com.blankj.utilcode.util.SPUtils;
import com.feb.lib_utils.constant.SPConstants;

public class SpUtils {
    public static String getToken() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getString(SPConstants.TOKEN);
    }
}
