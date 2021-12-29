package com.eduman.ui.fragments.subject

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.data.room.entitiy.Subject
import com.google.android.material.textview.MaterialTextView

class SubjectDetailFragment : EduManFragment(
    R.layout.fragment_subject_detail,
    "SubjectDetail"
) {

    private val arguments: SubjectDetailFragmentArgs by navArgs()
    private var subject: Subject? = null

    private var buttonBack: ImageView? = null
    private var textViewSubjectName: MaterialTextView? = null
    private var textViewTeacherName: MaterialTextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        subject = arguments.subject
        buttonBack = activity?.findViewById(R.id.fragmentSubjectDetailsButtonBack)
        textViewSubjectName = activity?.findViewById(R.id.fragmentSubjectDetailsTextViewSubjectName)
        textViewTeacherName = activity?.findViewById(R.id.fragmentSubjectDetailsTextViewTeacherName)

        textViewSubjectName?.text = subject?.title
        textViewTeacherName?.text = subject?.teacherName

        buttonBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}