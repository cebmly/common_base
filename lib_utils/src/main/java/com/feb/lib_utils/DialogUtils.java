package com.feb.lib_utils;



import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


import com.blankj.utilcode.util.ActivityUtils;



public class DialogUtils {


    public static void showDialogFragment(Object object) {
        showDialogFragment(object, null);
    }

    public static void showDialogFragment(Object object, FragmentManager fragmentManager) {
        if (object instanceof DialogFragment) {
            if (fragmentManager == null && ActivityUtils.getTopActivity() instanceof FragmentActivity) {
                fragmentManager = ((FragmentActivity) ActivityUtils.getTopActivity()).getSupportFragmentManager();
            }
            if (fragmentManager != null && !fragmentManager.isStateSaved()) {
                ((DialogFragment) object).show(fragmentManager, object.getClass().getSimpleName());
            }
        }
    }
}


