package com.eduman.core.components.textfield

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.eduman.core.components.textfield.validator.implementation.FloatValidator

class FloatTextField : BaseTextField {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        super.addValidator(FloatValidator(context))
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        this.editText?.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    }

    fun getValue(): Float {
        return try {
            this.editText?.text.toString().trim().replace(',', '.').toFloat()
        } catch (e: NumberFormatException) {
            0.0F
        }
    }

    fun setValue(value: Float) {
        this.editText?.setText(value.toString())
    }

}