package com.feb.module_home.find.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.feb.lib_common.view.ItemTouchHelperCallback
import com.feb.module_home.R
import com.luck.picture.lib.entity.LocalMedia
import java.util.Collections

class TrendPictureRvAdapter(val mediaType: Int) : RecyclerView.Adapter<BaseViewHolder>(),
    ItemTouchHelperCallback.DragLister {

    val maxVideo = 1

    val maxPicture = 9

    var data: ArrayList<LocalMedia> = ArrayList()

    companion object {
        const val ADD_VIEW_TYPE = 1
        const val NORMAL_VIEW_TYPE = 2
    }

    override fun getItemCount(): Int {
        return if (!isMax()) {
            data.size + 1
        } else {
            data.size
        }
    }

    private fun isMax(): Boolean {
        return if (mediaType == 1) //图片
            data.size == maxPicture
        else    //视频
            data.size == maxVideo
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        if (itemViewType == NORMAL_VIEW_TYPE) {
            val localMedia = data[position]
            val mimeType = localMedia.mimeType
            holder.setVisible(R.id.iv_frame, false)
            holder.setVisible(R.id.iv_delete, true)
            Glide.with(holder.getView<ImageView>(R.id.iv_trend_content).context).load(localMedia.path)
                .into(holder.getView<ImageView>(R.id.iv_trend_content))
        } else {

            holder.getView<ImageView>(R.id.iv_trend_content).setBackgroundResource(com.feb.lib_common.R.mipmap.common_ic_add_photo)
        }
        holder.getView<ImageView>(R.id.iv_delete).setOnClickListener {
            onItemChildClickListener?.onClick(it, position)
        }
        holder.getView<ImageView>(R.id.iv_trend_content).setOnClickListener {
            onItemChildClickListener?.onClick(it, position)
        }
    }
    var onItemChildClickListener: OnItemChildClickListener? = null

    interface OnItemChildClickListener {
        fun onClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val context = parent.context

        return if (viewType == ADD_VIEW_TYPE) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.find_add_photo_item, parent, false)

            BaseViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(context).inflate(R.layout.find_add_photo_item, parent, false)
            BaseViewHolder(view)
        }
    }

    fun setNewData(newData: ArrayList<LocalMedia>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (!isMax() && position == data.size) {
            ADD_VIEW_TYPE
        } else {
            NORMAL_VIEW_TYPE
        }
    }
    override fun onDragMoveSwap(adapterPosition: Int, adapterPosition1: Int): Boolean {
        //得到当拖拽的viewHolder的Position
        var itemViewType = getItemViewType(adapterPosition)
        if (itemViewType == 1) { //拖拽的是添加按键返回false
            return false
        }

        //拿到当前拖拽到的item的viewHolder
        itemViewType = getItemViewType(adapterPosition1)
        if (itemViewType == 1) { //拖拽的是添加按键返回false
            return false
        }
        if (adapterPosition < adapterPosition1) {
            for (i in adapterPosition until adapterPosition1) {
                Collections.swap(data, i, i + 1)
            }
        } else {
            for (i in adapterPosition downTo adapterPosition1 + 1) {
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(adapterPosition, adapterPosition1)
        return true
    }


    override fun onSwipeDelete(adapterPosition: Int): Boolean {
        return false
    }

    override fun onSwiped(adapterPosition: Int) {

    }
}