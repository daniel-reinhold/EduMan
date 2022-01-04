package com.eduman.ui.fragments.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.core.util.GeneralUtil
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.viewmodel.SubjectViewModel
import com.eduman.ui.dialogs.ColorPickerDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectFormFragment : Fragment(R.layout.fragment_subject_form) {

    private val subjectViewModel: SubjectViewModel by viewModels()

    private var textFieldSubjectName: TextInputLayout? = null
    private var textFieldTeacherName: TextInputLayout? = null

    private var colorView: View? = null
    private var buttonSelectColor: MaterialButton? = null

    private var buttonSave: MaterialButton? = null

    var subject = Subject(
        title = "",
        teacherName = "",
        color = ColorPickerDialog.INITIAL_COLOR.toInt()
    )

    private val colorPickerDialogCallback = object : ColorPickerDialog.ColorPickerDialogListener {
        override fun onColorSelected(color: Int) {
            colorView?.backgroundTintList = GeneralUtil.getColorStateList(color)
            subject.color = color
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        textFieldSubjectName = activity?.findViewById(R.id.fragmentSubjectFormTextFieldSubjectName)
        textFieldTeacherName = activity?.findViewById(R.id.fragmentSubjectFormTextFieldTeacherName)
        colorView = activity?.findViewById(R.id.fragmentSubjectFormColorView)
        buttonSelectColor = activity?.findViewById(R.id.fragmentSubjectFormButtonSelectColor)
        buttonSave = activity?.findViewById(R.id.fragmentSubjectFormButtonSave)

        textFieldSubjectName?.editText?.doAfterTextChanged {
            subject.title = it.toString()
            validate()
        }

        textFieldTeacherName?.editText?.doAfterTextChanged {
            subject.teacherName = it.toString()
            validate()
        }

        buttonSelectColor?.setOnClickListener {
            activity?.let {
                ColorPickerDialog(it, colorPickerDialogCallback).show()
            }
        }

        buttonSave?.setOnClickListener {
            subjectViewModel.insert(subject).invokeOnCompletion {
                findNavController().navigateUp()
            }
        }
    }

    private fun validate() {
        var valid = true

        if (textFieldSubjectName?.editText?.text?.toString()?.isEmpty() == true ||
            textFieldTeacherName?.editText?.text?.toString()?.isEmpty() == true) {
            valid = false
        }

        buttonSave?.isEnabled = valid
    }

}