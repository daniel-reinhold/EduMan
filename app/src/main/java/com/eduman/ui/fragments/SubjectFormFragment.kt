package com.eduman.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eduman.R
import com.eduman.core.EduManFragment

class SubjectFormFragment : EduManFragment(R.layout.fragment_subject_form, "SubjectForm") {

    private var navController: NavController? = null
    private var buttonClose: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        navController = findNavController()
        buttonClose = activity?.findViewById(R.id.fragmentSubjectFormButtonClose)

        buttonClose?.setOnClickListener {
            navController?.navigateUp()
        }
    }

}