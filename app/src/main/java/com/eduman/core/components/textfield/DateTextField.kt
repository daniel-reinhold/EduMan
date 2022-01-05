package com.eduman.core.components.textfield

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.eduman.R
import com.eduman.core.util.formatter.DateFormatter
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.lang.IllegalArgumentException
import java.lang.NullPointerException
import java.util.*

class DateTextField : BaseTextField {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var selectedDate = Calendar.getInstance()
    private var fragmentManager: FragmentManager? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        this.editText?.apply {
            this.inputType = InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE
            this.isEnabled = false
            this.setTextColor(
                ContextCompat.getColorStateList(context, R.color.text_color_text_field_with_selector)
            )
        }
        this.setStartIconDrawable(R.drawable.icon_calendar)
        this.setStartIconOnClickListener {
            showDatePicker()
        }

        this.endIconMode = END_ICON_CUSTOM
        this.setEndIconDrawable(R.drawable.icon_close)
        this.isEndIconVisible = false

        this.setEndIconOnClickListener {
            selectedDate = Calendar.getInstance()
            this.editText?.setText("")
            this.isEndIconVisible = false
        }
    }

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    fun getValue(): Calendar {
        return selectedDate
    }

    private fun showDatePicker() {
        val instance = this
        super.alreadyFocused = true

        MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.select_date)
            .setSelection(selectedDate.timeInMillis)
            .setCalendarConstraints(
                CalendarConstraints.Builder().setValidator(
                    DateValidatorPointForward.now()
                ).build()
            )
            .build().apply {
                addOnPositiveButtonClickListener { timestamp ->
                    selectedDate.timeInMillis = timestamp
                    instance.editText?.setText(
                        DateFormatter.formatDateDefault(context, selectedDate.time)
                    )
                    instance.isEndIconVisible = true
                }
                if (instance.fragmentManager != null) {
                    instance.fragmentManager?.let {
                        show(it, "DatePicker")
                    }
                } else {
                    throw NullPointerException("FragmentManager mustn't be empty")
                }
            }
    }

}