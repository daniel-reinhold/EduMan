package com.eduman.core.components.textfield

import android.content.Context
import android.util.AttributeSet
import java.lang.NumberFormatException

class IntegerTextField : BaseTextField {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun getValue(): Int {
        return try {
            this.editText?.text?.toString()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            0
        }
    }

    fun setValue(value: Int) {
        this.editText?.setText(value.toString())
    }

}