package com.eduman.ui.fragments.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_SUBJECT
import com.eduman.core.EduManFragment
import com.eduman.core.components.textfield.BaseTextField
import com.eduman.core.components.textfield.TextField
import com.eduman.core.components.textfield.validator.implementation.PresenceValidator
import com.eduman.core.util.GeneralUtil
import com.eduman.core.util.extensions.orZero
import com.eduman.data.room.entity.Subject
import com.eduman.data.room.viewmodel.SubjectViewModel
import com.eduman.ui.dialogs.ColorPickerDialog
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectFormFragment : EduManFragment("SubjectForm") {

    // <editor-fold desc="Private variables" defaultstate="collapsed">

    private val subjectViewModel: SubjectViewModel by viewModels()

    private var textFieldSubjectName: TextField? = null
    private var textFieldTeacherName: TextField? = null

    private var colorView: View? = null
    private var buttonSelectColor: MaterialButton? = null

    private var buttonSave: MaterialButton? = null

    var selectedColor = ColorPickerDialog.DEFAULT_INITIAL_COLOR.toInt()

    private var subjectToEdit: Subject? = null

    // </editor-fold>

    // <editor-fold desc="Callback implementations" defaultstate="collapsed">

    private val colorPickerDialogCallback = object : ColorPickerDialog.ColorPickerDialogListener {
        override fun onColorSelected(color: Int) {
            colorView?.backgroundTintList = GeneralUtil.getColorStateList(color)
            selectedColor = color
        }

    }

    // </editor-fold>

    // <editor-fold desc="Lifecycle methods" defaultstate="collapsed">

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subject_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    // </editor-fold>

    // <editor-fold desc="Initialization methods" defaultstate="collapsed">

    /**
     * This method initializes the view
     */
    private fun initialize() {
        subjectToEdit = arguments?.getParcelable(KEY_SUBJECT)
        textFieldSubjectName = activity?.findViewById(R.id.fragmentSubjectFormTextFieldSubjectName)
        textFieldTeacherName = activity?.findViewById(R.id.fragmentSubjectFormTextFieldTeacherName)
        colorView = activity?.findViewById(R.id.fragmentSubjectFormColorView)
        buttonSelectColor = activity?.findViewById(R.id.fragmentSubjectFormButtonSelectColor)
        buttonSave = activity?.findViewById(R.id.fragmentSubjectFormButtonSave)

        setActionBarTitle(R.string.create_subject)

        activity?.let {
            textFieldSubjectName?.addValidator(PresenceValidator(it))
            textFieldTeacherName?.addValidator(PresenceValidator(it))
        }

        subjectToEdit?.let {
            textFieldSubjectName?.setValue(it.title)
            textFieldTeacherName?.setValue(it.teacherName)
            colorView?.backgroundTintList = GeneralUtil.getColorStateList(it.color)
            selectedColor = it.color
            buttonSave?.isEnabled = true
            setActionBarTitle(R.string.edit_subject)
        }

        textFieldSubjectName?.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
            override fun onTextChange(text: String) {
                validate()
            }
        })

        textFieldTeacherName?.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
            override fun onTextChange(text: String) {
                validate()
            }
        })

        buttonSelectColor?.setOnClickListener {
            activity?.let {
                ColorPickerDialog(it, colorPickerDialogCallback, selectedColor).show()
            }
        }

        buttonSave?.setOnClickListener {
            val subject = Subject(
                id = subjectToEdit?.id,
                title = textFieldSubjectName?.getValue().orEmpty(),
                teacherName = textFieldTeacherName?.getValue().orEmpty(),
                color = selectedColor
            )
            subjectViewModel.insert(subject).invokeOnCompletion {
                findNavController().navigateUp()
            }
        }
    }

    // </editor-fold>

    // <editor-fold desc="Helper methods" defaultstate="collapsed">

    /**
     * This methods validates the form fields an sets the Save-Button
     * enabled or disabled depending on the result
     */
    private fun validate() {
        val errorCount =
            textFieldSubjectName?.getErrorCount().orZero() +
            textFieldTeacherName?.getErrorCount().orZero()

        buttonSave?.isEnabled = errorCount == 0
    }

    // </editor-fold>

}