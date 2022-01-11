package com.eduman.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.eduman.R
import com.eduman.core.EduManFragment
import com.google.android.material.card.MaterialCardView

class SettingsOverviewFragment : EduManFragment("SettingsOverview") {

    private var cardAppSettings: MaterialCardView? = null
    private var cardAppInfo: MaterialCardView? = null
    private var cardLibraries: MaterialCardView? = null
    private var cardDataProtection: MaterialCardView? = null
    private var cardImprint: MaterialCardView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        cardAppSettings = activity?.findViewById(R.id.fragmentSettingsOverviewCardAppSettings)
        cardAppInfo = activity?.findViewById(R.id.fragmentSettingsOverviewCardAppInformation)
        cardLibraries = activity?.findViewById(R.id.fragmentSettingsOverviewCardLibraries)
        cardDataProtection = activity?.findViewById(R.id.fragmentSettingsOverviewCardDataProtection)
        cardImprint = activity?.findViewById(R.id.fragmentSettingsOverviewCardImprint)

        cardAppSettings?.setOnClickListener {
            findNavController().navigate(R.id.action_settingsOverviewFragment_to_appSettingsFragment)
        }

        cardAppInfo?.setOnClickListener {
            findNavController().navigate(R.id.action_settingsOverviewFragment_to_appInfoFragment)
        }

        cardLibraries?.setOnClickListener {
            findNavController().navigate(R.id.action_settingsOverviewFragment_to_librariesFragment)
        }

        cardDataProtection?.setOnClickListener {
            findNavController().navigate(R.id.action_settingsOverviewFragment_to_dataProtectionFragment)
        }

        cardImprint?.setOnClickListener {
            findNavController().navigate(R.id.action_settingsOverviewFragment_to_imprintFragment)
        }
    }

}