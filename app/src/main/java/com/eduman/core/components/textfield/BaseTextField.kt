package com.eduman.core.components.textfield

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.doAfterTextChanged
import com.eduman.core.components.textfield.validator.Validator
import com.google.android.material.textfield.TextInputLayout

abstract class BaseTextField : TextInputLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var textChangeListener: TextChangeListener? = null
    private val validators: MutableList<Validator> = mutableListOf()
    private var currentErrorMessage: String? = null
    protected var alreadyFocused = false

    interface TextChangeListener {
        fun onTextChange(text: String)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        this.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!alreadyFocused && hasFocus) {
                alreadyFocused = true
            }
        }

        this.editText?.doAfterTextChanged {
            if (alreadyFocused) {
                textChangeListener?.onTextChange(it.toString())
            }
        }

    }

    fun setOnTextChangeListener(listener: TextChangeListener) {
        this.textChangeListener = listener
    }

    fun addValidator(vararg validator: Validator) {
        validator.forEach {
            this.validators.add(it)
        }
    }

    fun getErrorCount(): Int {
        var errorCount = 0

        validators.forEach { validator ->
            if (!validator.isValid(this.editText?.text.toString())) {
                errorCount++
                currentErrorMessage = validator.getErrorMessage()
                if (alreadyFocused) {
                    showError()
                }
                return@forEach
            }
        }

        if (errorCount == 0) {
            currentErrorMessage = null
            reset()
        }

        return errorCount
    }

    private fun showError() {
        currentErrorMessage?.let { error ->
            this.isErrorEnabled = true
            this.error = error
        }
    }

    private fun reset() {
        this.isErrorEnabled = false
        this.error = null
        this.currentErrorMessage = null
    }

}