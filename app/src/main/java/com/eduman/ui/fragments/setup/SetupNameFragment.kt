package com.eduman.ui.fragments.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.core.components.textfield.BaseTextField
import com.eduman.core.components.textfield.TextField
import com.eduman.core.components.textfield.validator.implementation.PresenceValidator
import com.eduman.ui.activities.SetupActivity
import com.eduman.ui.fragments.setup.viewmodel.SetupViewModel

class SetupNameFragment : EduManFragment("SetupNameFragment") {

    private var setupActivity: SetupActivity? = null
    private var textFieldName: TextField? = null
    private val setupViewModel: SetupViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_setup_name, container, false)
    }

    override fun onResume() {
        super.onResume()

        initialize()
    }

    private fun initialize() {
        setupActivity = activity as? SetupActivity
        textFieldName = activity?.findViewById(R.id.fragmentSetupNameTextFieldName)

        setActionBarTitle(R.string.setup_assistant_step1)
        setupActivity?.let {
            it.toggleBottomNavigationBar()
            it.toggleButton(SetupActivity.Button.BACK, false)
            it.toggleButton(SetupActivity.Button.NEXT, textFieldName?.getValue()?.isNotBlank() == true)
        }

        activity?.let {
            textFieldName?.addValidator(PresenceValidator(it))
            textFieldName?.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
                override fun onTextChange(text: String) {
                    validate()
                    setupViewModel.setName(text)
                }
            })
        }
    }

    private fun validate() {
        val errorCount = textFieldName?.getErrorCount()

        setupActivity?.toggleButton(SetupActivity.Button.NEXT, errorCount == 0)
    }

}