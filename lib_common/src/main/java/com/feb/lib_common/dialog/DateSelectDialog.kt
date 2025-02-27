package com.feb.lib_common.dialog

import android.content.Context
import com.feb.lib_common.R
import com.feb.lib_common.base.BaseDialog
import com.feb.lib_common.databinding.CommonDialogDateSelectBinding

class DateSelectDialog(context: Context) : BaseDialog<CommonDialogDateSelectBinding>(context)  {
    override fun getLayoutId(): Int {
        return R.layout.common_dialog_date_select
    }

    override fun initView() {

    }

    override fun initData() {

    }
}