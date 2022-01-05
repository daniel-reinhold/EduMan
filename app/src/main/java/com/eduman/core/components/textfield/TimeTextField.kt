package com.eduman.core.components.textfield

import android.content.Context
import android.text.InputType
import android.text.format.DateFormat
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.eduman.R
import com.eduman.core.util.formatter.DateFormatter
import com.eduman.core.util.formatter.TimeFormatter
import com.eduman.data.util.Time
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.lang.IllegalArgumentException
import java.lang.NullPointerException
import java.util.*

class TimeTextField : BaseTextField {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var selectedTime = Calendar.getInstance()
    private var fragmentManager: FragmentManager? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        this.editText?.apply {
            this.inputType = InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_TIME
            this.isEnabled = false
            this.setTextColor(
                ContextCompat.getColorStateList(context, R.color.text_color_text_field_with_selector)
            )
        }
        this.setStartIconDrawable(R.drawable.icon_clock)
        this.setStartIconOnClickListener {
            showTimePicker()
        }

        this.endIconMode = END_ICON_CUSTOM
        this.setEndIconDrawable(R.drawable.icon_close)
        this.isEndIconVisible = false

        this.setEndIconOnClickListener {
            selectedTime = Calendar.getInstance()
            this.editText?.setText("")
            this.isEndIconVisible = false
        }
    }

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    fun getValue(): Calendar {
        return selectedTime
    }

    private fun showTimePicker() {
        val instance = this
        super.alreadyFocused = true

        MaterialTimePicker.Builder()
            .setTitleText(R.string.select_time)
            .setHour(selectedTime.get(Calendar.HOUR_OF_DAY))
            .setMinute(selectedTime.get(Calendar.MINUTE))
            .setTimeFormat(if (DateFormat.is24HourFormat(context)) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H)
            .build().apply {
                addOnPositiveButtonClickListener {
                    selectedTime.set(Calendar.HOUR_OF_DAY, this.hour)
                    selectedTime.set(Calendar.MINUTE, this.minute)

                    instance.editText?.setText(
                        TimeFormatter.formatTimeDefault(context, selectedTime.time)
                    )
                    instance.isEndIconVisible = true
                }
                if (instance.fragmentManager != null) {
                    instance.fragmentManager?.let {
                        show(it, "TimePicker")
                    }
                } else {
                    throw NullPointerException("FragmentManager mustn't be empty")
                }
            }
    }

}