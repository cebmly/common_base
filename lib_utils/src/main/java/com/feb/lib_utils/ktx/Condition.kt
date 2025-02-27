package com.feb.lib_utils.ktx

object Condition {
    private fun throwException(exceptionMsg: String?, exceptionClass: Class<out RuntimeException>) {
        var exception = try {
            val constructor = exceptionClass.getConstructor(String::class.java)
            constructor.newInstance(exceptionMsg)
        } catch (e: Exception) {
            RuntimeException(exceptionMsg)
        }
        throw exception
    }

    /**
     * 确保不为Null
     */
    fun ensureNotNull(obj: Any?, exceptionMsg: String?) {
        ensureNotNull(obj, exceptionMsg, IllegalArgumentException::class.java)
    }

    fun ensureNotNull(obj: Any?, exceptionMsg: String?, exceptionClass: Class<out RuntimeException>) {
        if (obj == null) {
            throwException(exceptionMsg, exceptionClass)
        }
    }

    /**
     * 确保字符串不为空
     */
    fun ensureNotEmpty(text: CharSequence?, exceptionMsg: String?) {
        ensureNotEmpty(text, exceptionMsg, IllegalArgumentException::class.java)
    }

    fun ensureNotEmpty(text: CharSequence?, exceptionMsg: String?, exceptionClass: Class<out RuntimeException>) {
        if (StringUtils.isEmpty(text)) {
            throwException(exceptionMsg, exceptionClass)
        }
    }

    /**
     * 确保特定的iterable不为空
     */
    fun ensureNotEmpty(iterable: Iterable<*>, exceptionMsg: String?) {
        ensureNotEmpty(iterable, exceptionMsg, IllegalArgumentException::class.java)
    }

    fun ensureNotEmpty(iterable: Iterable<*>, exceptionMsg: String?, exceptionClass: Class<out RuntimeException>) {
        if (!iterable.iterator().hasNext()) {
            throwException(exceptionMsg, exceptionClass)
        }
    }

    /**
     * 确保特定的整型值至少与参考值一样大
     * @param value 特定值
     * @param referenceValue 参考值
     */
    fun ensureAtLeast(value: Int, referenceValue: Int, exceptionMsg: String?) {
        ensureAtLeast(value, referenceValue, exceptionMsg, IllegalArgumentException::class.java)
    }

    fun ensureAtLeast(value: Int, referenceValue: Int, exceptionMsg: String?, exceptionClass: Class<out RuntimeException>) {
        if (value < referenceValue) {
            throwException(exceptionMsg, exceptionClass)
        }
    }

    /**
     * 确保特定的整型值最多等于参考值
     * @param value 特定值
     * @param referenceValue 参考值
     */
    fun ensureAtMaximum(value: Int, referenceValue: Int, exceptionMsg: String?) {
        ensureAtMaximum(value, referenceValue, exceptionMsg, IllegalArgumentException::class.java)
    }

    fun ensureAtMaximum(value: Int, referenceValue: Int, exceptionMsg: String?, exceptionClass: Class<out RuntimeException>) {
        if (value > referenceValue) {
            throwException(exceptionMsg, exceptionClass)
        }
    }
}