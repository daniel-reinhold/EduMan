package com.eduman.core.components.textfield.validator.implementation

import android.content.Context
import com.eduman.R
import com.eduman.core.components.textfield.validator.Validator

class PresenceValidator(context: Context) : Validator(context) {

    override fun isValid(delegate: String) = delegate.isNotBlank()

    override fun getErrorMessage() = context.getString(R.string.error_blank)
}