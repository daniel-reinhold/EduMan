package com.eduman.ui.fragments.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.eduman.R
import com.eduman.core.Constants.Companion.DEFAULT_PIN_LENGTH
import com.eduman.core.EduManFragment
import com.eduman.core.components.textfield.IntegerTextField
import com.eduman.ui.activities.SetupActivity
import com.eduman.ui.fragments.setup.viewmodel.SetupViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class SetupPinFragment : EduManFragment("SetupPinFragment") {

    private var setupActivity: SetupActivity? = null
    private val setupViewModel: SetupViewModel by activityViewModels()

    private var textFieldPin: TextInputLayout? = null
    private var buttonSkip: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_setup_pin, container, false)
    }

    override fun onResume() {
        super.onResume()
        initialize()
    }

    private fun initialize() {
        setupActivity = (activity as? SetupActivity)
        setupViewModel.setUsePin(true)
        textFieldPin = activity?.findViewById(R.id.fragmentSetupPinTextFieldPin)
        buttonSkip = activity?.findViewById(R.id.fragmentSetupPinButtonSkip)

        setActionBarTitle(R.string.setup_assistant_step2)
        setupActivity?.let {
            it.toggleBottomNavigationBar()
            it.toggleButton(SetupActivity.Button.BACK, true)
            it.toggleButton(
                SetupActivity.Button.NEXT,
                textFieldPin?.editText?.text?.length == DEFAULT_PIN_LENGTH
            )
        }

        buttonSkip?.setOnClickListener {
            setupViewModel.setUsePin(false)
            setupActivity?.setPage(SetupActivity.POSITION_SETUP_COMPLETE)
        }

        textFieldPin?.editText?.doAfterTextChanged {
            if (isValid()) {
                setupViewModel.setPIN(it.toString())
            }
        }
    }

    private fun isValid(): Boolean {
        val valid = textFieldPin?.editText?.text?.length == DEFAULT_PIN_LENGTH
        setupActivity?.toggleButton(SetupActivity.Button.NEXT, valid)

        return valid
    }

}