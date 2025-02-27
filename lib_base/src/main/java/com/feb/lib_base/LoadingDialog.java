package com.feb.lib_base;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.LoadingDialog);
        setContentView(R.layout.dialog_loading);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

}
