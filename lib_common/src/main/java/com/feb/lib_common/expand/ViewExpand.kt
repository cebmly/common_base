package com.feb.lib_common.expand

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.feb.lib_utils.OnClickUtils
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * View的扩展方法
 */

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}


// String 转 Int
fun String.toExceptionInt(): Int {
    var a = 0
    kotlin.runCatching {
        this.toInt()
    }.onSuccess {
        a = it
    }
    return a
}

// String 转 Long
fun String?.toExceptionLong(): Long {
    var a = 0L
    kotlin.runCatching {
        this?.toLong()
    }.onSuccess {
        a = it ?: 0L
    }
    return a
}



/**
 * 拦截多次点击的Click
 */
fun View.setOnFastClickListener(onClick: () -> Unit) {
    this.setOnClickListener {
        if (OnClickUtils.isFastClick()) {
            return@setOnClickListener
        }
        onClick()
    }
}

/**
 * 通用的EditText的输入之后的内容回调
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

/**
 * 通用的EditText的输入时的内容回调
 */
fun EditText.onTextChanged(onTextChange: () -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChange()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    })
}

/**
 * 倒计时
 */
fun startTimer(
    timer: Int,
    textView: TextView? = null,
    onTimer: (Int) -> Unit,
    onTimerEnd: (() -> Unit)? = null,
    isEnabled: Boolean = false
): CountDownTimer {
    return object : CountDownTimer(timer * 1000L, 1000L) {
        override fun onTick(p0: Long) {
            textView?.isEnabled = isEnabled
            onTimer((p0 / 1000).toInt())
        }

        override fun onFinish() {
            textView?.isEnabled = true
            onTimerEnd?.invoke()
            cancel()
        }
    }.start()
}


/**
 * 对于多行文字的展示，使用这种方式可以预先计算文本，防止UI线程耗时产品卡顿(只针对于多文本使用)
 */
fun AppCompatTextView.setTextFutureExt(text: String) = setTextFuture(
    PrecomputedTextCompat.getTextFuture(
        text, TextViewCompat.getTextMetricsParams(this), null
    )
)

fun AppCompatEditText.setTextFutureExt(text: String) = setText(
    PrecomputedTextCompat.create(text, TextViewCompat.getTextMetricsParams(this))
)


/**
 * 通用的计算进度条的方法
 * @param leftNum: 左
 * @param  rightNum：右
 */
fun calculationProgress(leftNum: String?, rightNum: String?, defaultNum: Int = 50): Int {
    try {
        if (leftNum == rightNum) {
            return 50
        }
        val result: Double = BigDecimal(leftNum).divide(
            BigDecimal(leftNum) + BigDecimal(rightNum), 2, RoundingMode.HALF_UP
        ).toDouble()
        return BigDecimal(result).multiply(BigDecimal(100)).toInt()
    } catch (e: Exception) {
        return defaultNum
    }
}

/**
 * 通用的计算进度条的方法
 * @param leftNum: 左
 * @param  allNum：总
 */
fun calculationProgress2(leftNum: String?, allNum: String?, defaultNum: Int = 0): Int {
    return try {
        val result: Double = BigDecimal(leftNum).divide(
            BigDecimal(allNum), 2, RoundingMode.HALF_UP
        ).toDouble()
        BigDecimal(result).multiply(BigDecimal(100)).toInt()
    } catch (e: Exception) {
        defaultNum
    }
}


