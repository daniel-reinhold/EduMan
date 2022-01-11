package com.eduman.ui.dialogs

import android.app.Activity
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import com.eduman.R
import com.eduman.core.components.textfield.FloatTextField
import com.eduman.core.components.textfield.validator.implementation.FloatRangeValidator
import com.eduman.core.components.textfield.validator.implementation.PresenceValidator
import com.eduman.data.room.entity.Grade
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

/**
 * This class represents a dialog to add a grade
 */
class AddGradeDialog(
    activity: Activity,
    callback: AddGradeDialogListener
) {

    /**
     * This interface represents a listener for the AddGradeDialog
     */
    interface AddGradeDialogListener {

        /**
         * This callback method is fired when the grade has been set
         * @param grade The grade which has been set - [Grade]
         */
        fun onSave(grade: Float, weighting: Float)
    }

    private val bottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetDialog)
    private val dialogContent = LayoutInflater.from(activity).inflate(
        R.layout.dialog_add_grade,
        activity.findViewById(android.R.id.content),
        false
    )
    private val textFieldGrade: FloatTextField = dialogContent.findViewById(R.id.dialogAddGradeTextFieldGrade)
    private val textFieldWeighting: FloatTextField = dialogContent.findViewById(R.id.dialogAddGradeTextFieldWeighting)
    private val buttonSave: MaterialButton = dialogContent.findViewById(R.id.dialogAddGradeButtonSave)

    init {
        textFieldGrade.addValidator(
            PresenceValidator(activity),
            FloatRangeValidator(activity, 1.0F, 6.0F)
        )
        textFieldWeighting.addValidator(PresenceValidator(activity))
        textFieldGrade.editText?.doAfterTextChanged { validate() }
        textFieldWeighting.editText?.doAfterTextChanged { validate() }

        buttonSave.setOnClickListener {
            callback.onSave(textFieldGrade.getValue(), textFieldWeighting.getValue())
            dismiss()
        }

        bottomSheetDialog.setContentView(dialogContent)
    }

    private fun validate() {
        val errorCount = textFieldGrade.getErrorCount() + textFieldWeighting.getErrorCount()
        buttonSave.isEnabled = errorCount == 0
    }

    fun show() = bottomSheetDialog.show()

    fun dismiss() = bottomSheetDialog.dismiss()

}