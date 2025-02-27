package com.feb.lib_common.view

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.feb.lib_common.R
import com.feb.lib_common.databinding.CommonViewCustomTopBarBinding

class CustomTopBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {
    private val mBinding: CommonViewCustomTopBarBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.common_view_custom_top_bar,
        this,
        true
    )
    val ivBack: ImageView
        get() = mBinding.ivBack
    val tvRight: TextView
        get() = mBinding.tvRight
    val ivRight: ImageView
        get() = mBinding.ivRight
    val tvTitle: TextView
        get() = mBinding.tvTitle

    val btnRight: TextView
        get() = mBinding.btnRight


    fun setTitle(title: String?) {
        mBinding.tvTitle.text = title
    }

    fun setRightText(txt: String?) {
        mBinding.tvRight.text = txt
    }

    fun setRightTxtVisible(b: Boolean) {
        mBinding.tvRight.visibility = if (b) VISIBLE else GONE
    }

    fun setColor(color: Int) {
        mBinding.tvTitle.setTextColor(color)
        mBinding.ivBack.setColorFilter(color)
    }

    fun setBackGroundColor(color: Int) {
        mBinding.parent.setBackgroundColor(color)
    }

    fun setRightIcon(drawable: Int) {
        mBinding.ivRight.setImageResource(drawable)
        mBinding.ivRight.visibility = VISIBLE
    }

    override fun onClick(view: View) {
        if (view.id == R.id.iv_back) {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTopBar)
        val title = typedArray.getString(R.styleable.CustomTopBar_TopBarTitle)
        val color = typedArray.getColor(R.styleable.CustomTopBar_TopBarThemeColor, Color.BLACK)
        typedArray.recycle()
        setTitle(title)
        setPadding(0, 0, 0, 0)
        mBinding.ivBack.setOnClickListener(this)
        setColor(color)
    }
}