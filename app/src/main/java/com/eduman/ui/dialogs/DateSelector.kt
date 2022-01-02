package com.eduman.ui.dialogs

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.eduman.R
import java.util.*

class DateSelector(
    context: Context,
    callback: DateSelectorListener
) {

    interface DateSelectorListener {
        fun onDateSet(date: Date)
    }

    private val datePickerDialogCallback = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        callback.onDateSet(
            Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }.time
        )
    }

    private val now = Calendar.getInstance()
    private val datePickerDialog = DatePickerDialog(
        context,
        R.style.DatePickerDialog,
        datePickerDialogCallback,
        now.get(Calendar.YEAR),
        now.get(Calendar.MONTH),
        now.get(Calendar.DAY_OF_MONTH)
    )

    fun show() = datePickerDialog.show()

    fun dismiss() = datePickerDialog.dismiss()

}