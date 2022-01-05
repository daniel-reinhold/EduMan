package com.eduman.core.components.textfield

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import java.lang.NumberFormatException

class IntegerTextField : BaseTextField {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        this.editText?.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_NORMAL
    }

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