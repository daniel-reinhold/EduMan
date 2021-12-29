package com.eduman.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.data.room.viewmodel.SubjectViewModel
import com.eduman.ui.adapters.SubjectsAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectFragment : EduManFragment(
    R.layout.fragment_subject,
    "SubjectsFragment"
) {

    private val subjectViewModel: SubjectViewModel by viewModels()

    private var navController: NavController? = null

    private var recyclerView: RecyclerView? = null
    private var adapterSubjects = SubjectsAdapter(listOf())

    private var containerEmpty: LinearLayout? = null

    private var buttonAddSubject: ExtendedFloatingActionButton? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }
    //fragmentSubjectsContainerEmpty
    @SuppressLint("NotifyDataSetChanged")
    private fun initialize() {
        navController = findNavController()
        recyclerView = activity?.findViewById(R.id.fragmentSubjectsRecyclerView)
        containerEmpty = activity?.findViewById(R.id.fragmentSubjectsContainerEmpty)
        buttonAddSubject = activity?.findViewById(R.id.fragmentSubjectsButtonAddSubject)

        recyclerView?.adapter = adapterSubjects

        subjectViewModel.getAll().observe(viewLifecycleOwner, { subjects ->
            if (subjects.isNotEmpty()) {
                adapterSubjects.apply {
                    this.subjects = subjects
                    this.notifyDataSetChanged()
                }

                containerEmpty?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
            } else {
                recyclerView?.visibility = View.GONE
                containerEmpty?.visibility = View.VISIBLE
            }
        })

        buttonAddSubject?.setOnClickListener {
            navController?.navigate(
                SubjectFragmentDirections.actionSubjectFragmentToSubjectFormFragment()
            )
        }
    }

}