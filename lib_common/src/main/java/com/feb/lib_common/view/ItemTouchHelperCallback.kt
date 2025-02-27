package com.feb.lib_common.view

import android.graphics.Canvas
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class ItemTouchHelperCallback(val listener: DragLister) : ItemTouchHelper.Callback() {
    private var canLongPressDragEnabled = false

    interface DragLister {
        fun onDragMoveSwap(adapterPosition: Int, adapterPosition1: Int): Boolean
        fun onSwipeDelete(adapterPosition: Int): Boolean
        fun onSwiped(adapterPosition: Int)
    }

    interface StartDragListener {
        /**
         * 该接口用于需要主动回调拖拽效果的
         * @param viewHolder
         */
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder?)
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (recyclerView.layoutManager is GridLayoutManager) {
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            makeMovementFlags(dragFlags, swipeFlags)
        } else {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = 0
            makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    /**
     * 重写拖拽不可用
     */
    override fun isLongPressDragEnabled(): Boolean {
        return canLongPressDragEnabled
    }

    fun setLongPressDragEnabled(boolean: Boolean) {
        canLongPressDragEnabled = boolean
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onDragMoveSwap(viewHolder.layoutPosition, target.layoutPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onSwipeDelete(viewHolder.layoutPosition)
    }

    /**
     * 长按选中Item的时候开始调用
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) return
        super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.itemView?.apply {
            alpha = 1F
            scaleX = 0.8F
            scaleY = 0.8F
        }
    }

    /**
     * 手指松开的时候还原
     */
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.apply {
            alpha = 1F
            scaleX = 1F
            scaleY = 1F
        }
        listener.onSwiped(viewHolder.layoutPosition)
//        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val value = 1 - abs(dX) / viewHolder.itemView.width
            viewHolder.itemView.apply {
                alpha = value
                scaleY = value
                scaleX = value
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    }
}