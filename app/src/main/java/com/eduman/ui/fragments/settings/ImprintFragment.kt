package com.eduman.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eduman.R
import com.eduman.core.EduManFragment

class ImprintFragment : EduManFragment("ImprintFragment") {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_imprint, container, false)
    }

}