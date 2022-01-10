package com.eduman.ui.fragments.setup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.core.util.SharedPreferencesUtil
import com.eduman.ui.activities.LaunchActivity
import com.eduman.ui.activities.SetupActivity
import com.eduman.ui.fragments.setup.viewmodel.SetupViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class SetupCompleteFragment : EduManFragment("SetupCompleteFragment") {

    private var setupActivity: SetupActivity? = null
    private val setupViewModel: SetupViewModel by activityViewModels()
    private var sharedPreferences: SharedPreferencesUtil? = null

    private var textViewName: MaterialTextView? = null
    private var textViewPIN: MaterialTextView? = null
    private var textViewPinNotUsed: MaterialTextView? = null

    private var buttonSave: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = layoutInflater.inflate(R.layout.fragment_setup_complete, container, false)

    override fun onResume() {
        super.onResume()
        initialize()
    }

    private fun initialize() {
        setupActivity = (activity as? SetupActivity)
        sharedPreferences = SharedPreferencesUtil(activity)
        textViewName = activity?.findViewById(R.id.fragmentSetupCompleteTextViewName)
        textViewPIN = activity?.findViewById(R.id.fragmentSetupCompleteTextViewPIN)
        textViewPinNotUsed = activity?.findViewById(R.id.fragmentSetupCompleteTextViewPINNotUsed)
        buttonSave = activity?.findViewById(R.id.fragmentSetupCompleteButtonSave)

        setActionBarTitle(R.string.setup_assistant_step3)
        setupActivity?.let {
            it.toggleBottomNavigationBar()
            it.toggleButton(SetupActivity.Button.BACK, true)
            it.toggleButton(SetupActivity.Button.NEXT, false)
        }

        setupViewModel.name.observe(viewLifecycleOwner, { name ->
            textViewName?.text = name
            sharedPreferences?.setUsername(name)
        })

        setupViewModel.usePin.observe(viewLifecycleOwner, { usePin ->
            textViewPIN?.visibility = if (usePin) View.VISIBLE else View.GONE
            textViewPinNotUsed?.visibility = if (usePin) View.GONE else View.VISIBLE
            sharedPreferences?.setUsePin(usePin)
        })

        setupViewModel.pin.observe(viewLifecycleOwner, { pin ->
            textViewPIN?.text = pin
            if (pin.isNotBlank()) {
                sharedPreferences?.setPin(pin)
            }
        })

        buttonSave?.setOnClickListener {
            activity?.let {
                it.startActivity(Intent(it, LaunchActivity::class.java))
            }
        }
    }

}