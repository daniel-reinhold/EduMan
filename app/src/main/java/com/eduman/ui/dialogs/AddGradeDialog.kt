package com.eduman.ui.dialogs

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import com.eduman.R
import com.eduman.data.room.entitiy.Grade
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import java.lang.NumberFormatException

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
        fun onSave(grade: Grade)
    }

    private val bottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetDialog)
    private val dialogContent = LayoutInflater.from(activity).inflate(
        R.layout.dialog_add_grade,
        activity.findViewById(android.R.id.content),
        false
    )
    private val textFieldGrade: TextInputLayout = dialogContent.findViewById(R.id.dialogAddGradeTextFieldGrade)
    private val textFieldWeighting: TextInputLayout = dialogContent.findViewById(R.id.dialogAddGradeTextFieldWeighting)
    private val buttonSave: MaterialButton = dialogContent.findViewById(R.id.dialogAddGradeButtonSave)
    private val grade = Grade(
        id = null,
        grade = 0.0F,
        weighting = 1.0F,
        subjectId = 0
    )

    init {
        textFieldGrade.editText?.doAfterTextChanged {
            validate()
            try {
                grade.grade = it.toString().toFloat()
            } catch (e: NumberFormatException) {
                Log.e("EduMan#AddGradeDialog", "Grade is not a valid float")
            }
        }

        textFieldWeighting.editText?.doAfterTextChanged {
            validate()
            try {
                grade.weighting = it.toString().toFloat()
            } catch (e: NumberFormatException) {
                Log.e("EduMan#AddGradeDialog", "Grade is not a valid float")
            }
        }

        buttonSave.setOnClickListener {
            callback.onSave(grade)
            dismiss()
        }

        bottomSheetDialog.setContentView(dialogContent)
    }

    private fun validate() {
        var valid = true

        if (textFieldGrade.editText?.text?.toString()?.isEmpty() == true ||
            textFieldWeighting.editText?.text?.toString()?.isEmpty() == true) {
            valid = false
        }

        buttonSave.isEnabled = valid
    }

    fun show() = bottomSheetDialog.show()

    fun dismiss() = bottomSheetDialog.dismiss()

}