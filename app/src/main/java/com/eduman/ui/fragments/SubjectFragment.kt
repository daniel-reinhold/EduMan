package com.eduman.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.viewmodel.SubjectViewModel
import com.eduman.ui.adapters.SubjectsAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectFragment : EduManFragment(R.layout.fragment_subject, "SubjectsFragment") {

    private val subjectViewModel: SubjectViewModel by viewModels()

    private var recyclerView: RecyclerView? = null
    private var buttonAddSubject: ExtendedFloatingActionButton? = null
    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        navController = findNavController()
        recyclerView = activity?.findViewById(R.id.fragmentSubjectsRecyclerView)
        buttonAddSubject = activity?.findViewById(R.id.fragmentSubjectsButtonAddSubject)

        buttonAddSubject?.setOnClickListener {
            val action = SubjectFragmentDirections.actionSubjectFragmentToSubjectFormFragment()
            navController?.navigate(action)
        }

        recyclerView?.adapter = SubjectsAdapter(listOf())
    }

}