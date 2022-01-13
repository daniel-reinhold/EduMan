package com.eduman.ui.fragments.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_SUBJECT_ID
import com.eduman.core.EduManFragment
import com.eduman.core.components.textfield.BaseTextField
import com.eduman.core.components.textfield.DateTextField
import com.eduman.core.components.textfield.TextField
import com.eduman.core.components.textfield.TimeTextField
import com.eduman.core.components.textfield.validator.implementation.PresenceValidator
import com.eduman.core.util.DateTimeUtil
import com.eduman.core.util.extensions.orZero
import com.eduman.data.room.entity.Test
import com.eduman.data.room.viewmodel.TestViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFormFragment : EduManFragment("TestFormFragment") {

    private var subjectId: Int? = null
    private val testViewModel: TestViewModel by viewModels()

    private var textFieldTopic: TextField? = null
    private var textFieldDate: DateTextField? = null
    private var textFieldTime: TimeTextField? = null
    private var buttonSave: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        subjectId = arguments?.getInt(KEY_SUBJECT_ID)
        textFieldTopic = activity?.findViewById(R.id.fragmentTestFormTextFieldTopic)
        textFieldDate = activity?.findViewById(R.id.fragmentTestFormTextFieldDate)
        textFieldTime = activity?.findViewById(R.id.fragmentTestFormTextFieldTime)
        buttonSave = activity?.findViewById(R.id.fragmentTestFormButtonSave)

        setActionBarTitle(R.string.create_test)

        activity?.baseContext?.let {
            textFieldTopic?.addValidator(PresenceValidator(it))
            textFieldDate?.addValidator(PresenceValidator(it))
            textFieldTime?.addValidator(PresenceValidator(it))
        }

        activity?.supportFragmentManager?.let { fm ->
            textFieldDate?.setFragmentManager(fm)
            textFieldTime?.setFragmentManager(fm)
        }

        textFieldTopic?.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
            override fun onTextChange(text: String) {
                validate()
            }
        })

        textFieldDate?.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
            override fun onTextChange(text: String) {
                validate()
            }
        })

        textFieldTime?.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
            override fun onTextChange(text: String) {
                validate()
            }
        })

        buttonSave?.setOnClickListener {
            val date = DateTimeUtil.dateAndTimeToDateTime(
                textFieldDate?.getValue(),
                textFieldTime?.getValue()
            )

            testViewModel.insert(
                Test(
                    topic = textFieldTopic?.getValue() ?: "",
                    date = date,
                    subjectId = subjectId ?: 0
                )
            ).invokeOnCompletion {
                findNavController().navigateUp()
            }
        }
    }

    private fun validate() {
        val errorCount = textFieldTopic?.getErrorCount().orZero() +
                         textFieldDate?.getErrorCount().orZero() +
                         textFieldTime?.getErrorCount().orZero()

        buttonSave?.isEnabled = errorCount == 0
    }

}