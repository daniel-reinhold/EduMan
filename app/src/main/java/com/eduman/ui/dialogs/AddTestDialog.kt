package com.eduman.ui.dialogs

import android.text.format.DateFormat
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentActivity
import com.eduman.R
import com.eduman.core.components.textfield.BaseTextField
import com.eduman.core.components.textfield.DateTextField
import com.eduman.core.components.textfield.TextField
import com.eduman.core.components.textfield.TimeTextField
import com.eduman.core.components.textfield.validator.implementation.PresenceValidator
import com.eduman.core.util.DateTimeUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

/**
 * This class represents a dialog to add a test
 */
class AddTestDialog(
    parentActivity: FragmentActivity,
    private val callback: AddTestDialogListener
) {

    /**
     * This interface represents a listener for the AddTestDialog
     */
    interface AddTestDialogListener {

        /**
         * This callback method is fired when the test has been set
         * @param topic The topic of the test - [String]
         * @param date The date when the test is written - [Long]
         */
        fun onSave(topic: String, date: Long)
    }

    private val bottomSheetDialog = BottomSheetDialog(parentActivity, R.style.BottomSheetDialog)
    private val dialogContent = LayoutInflater.from(parentActivity).inflate(
        R.layout.dialog_add_test,
        parentActivity.findViewById(android.R.id.content),
        false
    )

    private val textFieldTopic: TextField = dialogContent.findViewById(R.id.dialogAddTestTextFieldTopic)
    private val textFieldDate: DateTextField = dialogContent.findViewById(R.id.dialogAddTestTextFieldDate)
    private val textFieldTime: TimeTextField = dialogContent.findViewById(R.id.dialogAddTestTextFieldTime)
    private val buttonSave: MaterialButton = dialogContent.findViewById(R.id.dialogAddTestButtonSave)

    init {
        textFieldTopic.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
            override fun onTextChange(text: String) {
                validate()
            }
        })

        textFieldDate.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
            override fun onTextChange(text: String) {
                validate()
            }
        })

        textFieldTime.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
            override fun onTextChange(text: String) {
                validate()
            }
        })

        textFieldDate.setFragmentManager(parentActivity.supportFragmentManager)
        textFieldTime.setFragmentManager(parentActivity.supportFragmentManager)
        textFieldTopic.addValidator(PresenceValidator(parentActivity))
        textFieldDate.addValidator(PresenceValidator(parentActivity))
        textFieldTime.addValidator(PresenceValidator(parentActivity))

        buttonSave.setOnClickListener {
            val date = DateTimeUtil.dateAndTimeToDateTime(
                textFieldDate.getValue(),
                textFieldTime.getValue()
            )
            callback.onSave(textFieldTopic.getValue(), date.time)
            dismiss()
        }

        bottomSheetDialog.setContentView(dialogContent)
    }

    private fun validate() {
        val errorCount =
            textFieldTopic.getErrorCount() +
            textFieldDate.getErrorCount() +
            textFieldTime.getErrorCount()

        buttonSave.isEnabled = errorCount == 0
    }

    fun show() = bottomSheetDialog.show()

    fun dismiss() = bottomSheetDialog.dismiss()

}