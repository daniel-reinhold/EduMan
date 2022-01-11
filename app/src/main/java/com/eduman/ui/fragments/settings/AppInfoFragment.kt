package com.eduman.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eduman.BuildConfig
import com.eduman.R
import com.eduman.core.EduManFragment
import com.google.android.material.textview.MaterialTextView

class AppInfoFragment : EduManFragment("AppInfoFragment") {

    private var textViewVersion: MaterialTextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        textViewVersion = activity?.findViewById(R.id.fragmentAppInfoTextViewVersion)

        textViewVersion?.text = BuildConfig.VERSION_NAME
    }

}