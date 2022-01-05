package com.eduman.core.components.textfield.validator.implementation

import android.content.Context
import com.eduman.R
import com.eduman.core.components.textfield.validator.Validator
import java.lang.NumberFormatException

class FloatRangeValidator(
    context: Context,
    private val from: Float,
    private val to: Float
) : Validator(context) {

    override fun isValid(delegate: String): Boolean {
        return try {
            delegate.toFloat() in from..to
        } catch (e: NumberFormatException) {
            true
        }
    }

    override fun getErrorMessage() = context.getString(R.string.error_not_in_range, from, to)
}