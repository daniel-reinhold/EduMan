package com.eduman.ui.fragments.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.ui.activities.SetupActivity
import com.google.android.material.button.MaterialButton

class SetupStartFragment : EduManFragment("SetupCompleteFragment") {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_setup_start, container, false)
    }

    override fun onResume() {
        (activity as? SetupActivity)?.toggleBottomNavigationBar(false)

        activity?.findViewById<MaterialButton>(R.id.fragmentSetupStartButtonStart)?.setOnClickListener {
            (activity as? SetupActivity)?.start()
        }

        super.onResume()
    }

}