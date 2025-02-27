package com.feb.module_home.mine.adapter

import android.graphics.Color
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.feb.lib_common.expand.toGone
import com.feb.lib_common.expand.toVisible
import com.feb.module_home.R

class RechargeDiscountDetailAdapter : BaseQuickAdapter<String,BaseViewHolder>(R.layout.dialog_recharge_currency_item) {
    override fun convert(holder: BaseViewHolder, item: String) {

        holder.apply {
            if (holder.adapterPosition == 0 ) {


            }

            when(holder.adapterPosition) {

                0 -> {
                    getView<ConstraintLayout>(R.id.cl_bg_show).setBackgroundResource(R.drawable.shape_recharge_bg_new_user_only)
                    getView<TextView>(R.id.tv_recharge_discount).toVisible()
                    getView<TextView>(R.id.tv_discount).toGone()
                    getView<TextView>(R.id.tv_recharge_discount).setBackgroundResource(R.drawable.shape_recharge_bg_new_user_only2)
                    getView<TextView>(R.id.tv_recharge_discount).text = "New users only! "
                    getView<TextView>(R.id.tv_amount_required).setTextColor(Color.WHITE)
                }
                1 -> {
                    getView<ConstraintLayout>(R.id.cl_bg_show).setBackgroundResource(R.drawable.shape_recharge_bg_limited)
                    getView<TextView>(R.id.tv_recharge_discount).toVisible()
                    getView<TextView>(R.id.tv_discount).toVisible()
                    getView<TextView>(R.id.tv_recharge_discount).setBackgroundResource(R.drawable.shape_recharge_bg_limited2)
                    getView<TextView>(R.id.tv_recharge_discount).text = "59:59:00"
                }
                else -> {
                    getView<ConstraintLayout>(R.id.cl_bg_show).setBackgroundResource(R.drawable.shape_recharge_bg_ordinary)
                    getView<TextView>(R.id.tv_recharge_discount).toGone()
                    getView<TextView>(R.id.tv_discount).toGone()
                }
            }
        }
    }
}