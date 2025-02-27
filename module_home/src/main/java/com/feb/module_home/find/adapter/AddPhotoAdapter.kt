package com.feb.module_home.find.adapter

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.feb.module_home.R


class AddPhotoAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.find_add_photo_item) {


     val ITEM_TYPE_ADD: Int = 1
     val ITEM_TYPE_IMAGE: Int = 0
     val MAX_COUNT = 9




    override fun convert(holder: BaseViewHolder, item: String) {
        if (getItemViewType(holder.getAdapterPosition()) === ITEM_TYPE_ADD) {
            // 设置添加按钮（比如显示一个 + 按钮图标）
            holder.setImageResource(R.id.iv_trend_content, com.feb.lib_common.R.mipmap.common_ic_add_photo) // 显示“添加”按钮
            holder.getView<ImageView>(R.id.iv_delete).setVisibility(View.GONE) // 隐藏删除按钮
        } else {
            // 显示图片
            Glide.with(context).load(item)
                .into((holder.getView(R.id.iv_trend_content) as ImageView)!!) // 显示图片
            holder.getView<ImageView>(R.id.iv_delete).setVisibility(View.VISIBLE) // 显示删除按钮

        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
        return if ((position === data.size && data.size < MAX_COUNT)) ITEM_TYPE_ADD else ITEM_TYPE_IMAGE
    }
}