package com.eduman.core.components.textfield.validator

import android.content.Context

abstract class Validator(
    protected val context: Context
) {

    abstract fun isValid(delegate: String): Boolean
    abstract fun getErrorMessage(): String

}