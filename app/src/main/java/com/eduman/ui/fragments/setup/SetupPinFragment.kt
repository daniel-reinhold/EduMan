package com.eduman.ui.fragments.setup

import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.core.util.GeneralUtil
import com.eduman.ui.activities.SetupActivity
import com.eduman.ui.fragments.setup.viewmodel.SetupViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class SetupPinFragment : EduManFragment("SetupPinFragment") {

    private var setupActivity: SetupActivity? = null
    private val setupViewModel: SetupViewModel by activityViewModels()

    private var cardDigit1: MaterialCardView? = null
    private var cardDigit2: MaterialCardView? = null
    private var cardDigit3: MaterialCardView? = null
    private var cardDigit4: MaterialCardView? = null

    private var textFieldDigit1: EditText? = null
    private var textFieldDigit2: EditText? = null
    private var textFieldDigit3: EditText? = null
    private var textFieldDigit4: EditText? = null

    private var buttonClearTextFieldDigit1: ImageView? = null
    private var buttonClearTextFieldDigit2: ImageView? = null
    private var buttonClearTextFieldDigit3: ImageView? = null
    private var buttonClearTextFieldDigit4: ImageView? = null

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

        setupActivity?.setRunnableButtonNext {
            setPin()
        }

        cardDigit1 = activity?.findViewById(R.id.fragmentSetupPinCardDigit1)
        cardDigit2 = activity?.findViewById(R.id.fragmentSetupPinCardDigit2)
        cardDigit3 = activity?.findViewById(R.id.fragmentSetupPinCardDigit3)
        cardDigit4 = activity?.findViewById(R.id.fragmentSetupPinCardDigit4)
        textFieldDigit1 = activity?.findViewById(R.id.fragmentSetupPinDigit1)
        textFieldDigit2 = activity?.findViewById(R.id.fragmentSetupPinDigit2)
        textFieldDigit3 = activity?.findViewById(R.id.fragmentSetupPinDigit3)
        textFieldDigit4 = activity?.findViewById(R.id.fragmentSetupPinDigit4)
        buttonClearTextFieldDigit1 = activity?.findViewById(R.id.fragmentSetupPinButtonClearDigit1)
        buttonClearTextFieldDigit2 = activity?.findViewById(R.id.fragmentSetupPinButtonClearDigit2)
        buttonClearTextFieldDigit3 = activity?.findViewById(R.id.fragmentSetupPinButtonClearDigit3)
        buttonClearTextFieldDigit4 = activity?.findViewById(R.id.fragmentSetupPinButtonClearDigit4)
        buttonSkip = activity?.findViewById(R.id.fragmentSetupPinButtonSkip)

        buttonSkip?.setOnClickListener {
            setupViewModel.setUsePin(false)
            setupActivity?.setPage(SetupActivity.POSITION_SETUP_COMPLETE)
        }

        textFieldDigit1?.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                textFieldDigit2?.requestFocus()
                buttonClearTextFieldDigit1?.visibility = View.VISIBLE
            } else {
                buttonClearTextFieldDigit1?.visibility = View.GONE
            }

            validate()
        }

        textFieldDigit2?.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                textFieldDigit3?.requestFocus()
                buttonClearTextFieldDigit2?.visibility = View.VISIBLE
            } else {
                buttonClearTextFieldDigit2?.visibility = View.GONE
            }

            validate()
        }

        textFieldDigit3?.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                textFieldDigit4?.requestFocus()
                buttonClearTextFieldDigit3?.visibility = View.VISIBLE
            } else {
                buttonClearTextFieldDigit3?.visibility = View.GONE
            }

            validate()
        }

        textFieldDigit4?.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                inputManager?.hideSoftInputFromWindow(textFieldDigit4?.windowToken, 0)
                textFieldDigit4?.clearFocus()
                buttonClearTextFieldDigit4?.visibility = View.VISIBLE
            } else {
                buttonClearTextFieldDigit4?.visibility = View.GONE
            }

            validate()
        }

        toggleFocus(cardDigit1, textFieldDigit1)
        toggleFocus(cardDigit2, textFieldDigit2)
        toggleFocus(cardDigit3, textFieldDigit3)
        toggleFocus(cardDigit4, textFieldDigit4)

        clearTextFieldOnClick(buttonClearTextFieldDigit1, textFieldDigit1)
        clearTextFieldOnClick(buttonClearTextFieldDigit2, textFieldDigit2)
        clearTextFieldOnClick(buttonClearTextFieldDigit3, textFieldDigit3)
        clearTextFieldOnClick(buttonClearTextFieldDigit4, textFieldDigit4)

        setActionBarTitle(R.string.setup_assistant_step2)
        setupActivity?.let {
            it.toggleBottomNavigationBar()
            it.toggleButton(SetupActivity.Button.BACK, true)
            it.toggleButton(
                SetupActivity.Button.NEXT,
                textFieldDigit1?.text?.isNotBlank() == true &&
                    textFieldDigit2?.text?.isNotBlank() == true &&
                    textFieldDigit3?.text?.isNotBlank() == true &&
                    textFieldDigit4?.text?.isNotBlank() == true
            )
        }
    }

    private fun validate() {
        val valid = (textFieldDigit1?.text?.isNotBlank() ?: false) &&
                    (textFieldDigit2?.text?.isNotBlank() ?: false) &&
                    (textFieldDigit3?.text?.isNotBlank() ?: false) &&
                    (textFieldDigit4?.text?.isNotBlank() ?: false)

        setupActivity?.toggleButton(SetupActivity.Button.NEXT, valid)
    }

    private fun toggleFocus(container: MaterialCardView?, textField: EditText?) {
        textField?.setOnFocusChangeListener { _, hasFocus ->
            container?.context?.let {
                container.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it,
                        if (hasFocus) R.color.pin_focused_field else R.color.card_background
                    )
                )
            }
        }
    }

    private fun clearTextFieldOnClick(button: ImageView?, textField: EditText?) {
        button?.setOnClickListener {
            textField?.apply {
                setText("")
                requestFocus()
                activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            }
        }
    }

    private fun setPin() {
        val pin =   textFieldDigit1?.text?.toString().orEmpty() +
                    textFieldDigit2?.text?.toString().orEmpty() +
                    textFieldDigit3?.text?.toString().orEmpty() +
                    textFieldDigit4?.text?.toString().orEmpty()
        setupViewModel.setPIN(pin)
    }

}