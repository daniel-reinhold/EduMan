package com.eduman.core.components.textfield.validator.implementation

import android.content.Context
import com.eduman.R
import com.eduman.core.components.textfield.validator.Validator
import java.lang.NumberFormatException

class FloatValidator(context: Context) : Validator(context) {

    override fun isValid(delegate: String): Boolean {
        return try {
            delegate.toFloat()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    override fun getErrorMessage() = context.getString(R.string.error_not_a_float)
}