package com.eduman.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.core.util.SharedPreferencesUtil
import com.eduman.core.util.extensions.orFalse
import com.google.android.material.switchmaterial.SwitchMaterial

class AppSettingsFragment : EduManFragment("AppSettingsFragment") {

    private var sharedPreferences: SharedPreferencesUtil? = null

    private var switchUsePin: SwitchMaterial? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        sharedPreferences = SharedPreferencesUtil(activity)
        switchUsePin = activity?.findViewById(R.id.fragmentAppSettingsSwitchUsePin)

        switchUsePin?.isChecked = sharedPreferences?.usePin().orFalse()

        switchUsePin?.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences?.setUsePin(isChecked)
        }
    }

}