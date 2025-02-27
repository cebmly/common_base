package com.feb.lib_utils.ktx

object StringUtils {
    fun isEmpty(text: CharSequence?): Boolean = text?.isEmpty() ?: true

    fun isNotEmpty(text: CharSequence?): Boolean = !isEmpty(text)

    fun hasNotText(text: CharSequence?): Boolean = text?.let { it.isEmpty() || it.matches("\\s+".toRegex()) }
        ?: true

    fun hasText(text: CharSequence?): Boolean = !hasNotText(text)
}