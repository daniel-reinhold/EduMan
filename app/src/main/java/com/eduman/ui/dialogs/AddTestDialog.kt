package com.eduman.ui.dialogs

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentActivity
import com.eduman.R
import com.eduman.core.util.DateFormatUtil
import com.eduman.core.util.TimeFormatUtil
import com.eduman.data.room.entitiy.Test
import com.eduman.data.util.Time
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTestDialog(
    private val parentActivity: FragmentActivity,
    private val callback: AddTestDialogListener
) {

    interface AddTestDialogListener {
        fun onSave(topic: String, date: Long)
    }

    private val bottomSheetDialog = BottomSheetDialog(parentActivity, R.style.BottomSheetDialog)
    private val dialogContent = LayoutInflater.from(parentActivity).inflate(
        R.layout.dialog_add_test,
        parentActivity.findViewById(android.R.id.content),
        false
    )

    private val textFieldTopic: TextInputLayout = dialogContent.findViewById(R.id.dialogAddTestTextFieldTopic)
    private val textFieldDate: TextInputLayout = dialogContent.findViewById(R.id.dialogAddTestTextFieldDate)
    private val textFieldTime: TextInputLayout = dialogContent.findViewById(R.id.dialogAddTestTextFieldTime)
    private val buttonSave: MaterialButton = dialogContent.findViewById(R.id.dialogAddTestButtonSave)

    private var selectedDate = MaterialDatePicker.todayInUtcMilliseconds()
    private var selectedTime = Time()

    init {
        textFieldTopic.editText?.doAfterTextChanged { validate() }
        textFieldDate.setStartIconOnClickListener { showDatePicker() }
        textFieldTime.setStartIconOnClickListener { showTimePicker() }

        buttonSave.setOnClickListener {
            val date = Calendar.getInstance().apply {
                timeInMillis = selectedDate
                set(Calendar.HOUR_OF_DAY, selectedTime.hour)
                set(Calendar.MINUTE, selectedTime.minute)
            }

            callback.onSave(
                textFieldTopic.editText?.text.toString(),
                date.timeInMillis
            )
            dismiss()
        }

        bottomSheetDialog.setContentView(dialogContent)
    }

    private fun showDatePicker() {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.select_date)
            .setSelection(selectedDate)
            .setCalendarConstraints(
                CalendarConstraints.Builder().setValidator(
                    DateValidatorPointForward.now()
                ).build()
            )
            .build().apply {
                addOnPositiveButtonClickListener { timestamp ->
                    Date(timestamp).also { date ->
                        textFieldDate.editText?.setText(DateFormatUtil.formatDateDefault(parentActivity, date))
                        selectedDate = timestamp
                    }
                    validate()
                }
                show(parentActivity.supportFragmentManager, "DatePicker")
            }
    }

    private fun showTimePicker() {
        MaterialTimePicker.Builder()
            .setTitleText(R.string.select_time)
            .setHour(selectedTime.hour)
            .setMinute(selectedTime.minute)
            .setTimeFormat(
                if (DateFormat.is24HourFormat(parentActivity)) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
            )
            .build().apply {
                addOnPositiveButtonClickListener {
                    Time(
                        this.hour,
                        this.minute
                    ).also { time ->
                        textFieldTime.editText?.setText(TimeFormatUtil.formatTimeDefault(time))
                        selectedTime = time
                    }
                    validate()
                }
                show(parentActivity.supportFragmentManager, "TimePicker")
            }
    }

    private fun validate() {
        var valid = true

        if (textFieldTopic.editText?.text.toString().isBlank() ||
            textFieldDate.editText?.text.toString().isBlank() ||
            textFieldTime.editText?.text.toString().isBlank()) {
            valid = false
        }

        buttonSave.isEnabled = valid
    }

    fun show() = bottomSheetDialog.show()

    fun dismiss() = bottomSheetDialog.dismiss()

}