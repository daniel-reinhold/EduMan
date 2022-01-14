package com.eduman.core.components.textfield

import android.content.Context
import android.text.InputType
import android.util.AttributeSet

class TextField : BaseTextField {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        this.editText?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
    }

    fun getValue(): String {
        return this.editText?.text.toString()
    }

    fun setValue(value: String?) {
        this.editText?.setText(value)
    }

}