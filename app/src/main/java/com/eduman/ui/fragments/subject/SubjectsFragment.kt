package com.eduman.ui.fragments.subject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.data.room.viewmodel.SubjectViewModel
import com.eduman.ui.adapters.SubjectsAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectsFragment : EduManFragment("Subjects") {

    private val subjectViewModel: SubjectViewModel by viewModels()

    private var recyclerView: RecyclerView? = null
    private var adapterSubjects: SubjectsAdapter? = null

    private var containerEmpty: LinearLayout? = null

    private var buttonAddSubject: ExtendedFloatingActionButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subjects, container, false)

        initialize(view)

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initialize(view: View) {
        recyclerView = view.findViewById(R.id.fragmentSubjectsRecyclerView)
        containerEmpty = view.findViewById(R.id.fragmentSubjectsContainerEmpty)
        buttonAddSubject = view.findViewById(R.id.fragmentSubjectsButtonAddSubject)

        adapterSubjects = SubjectsAdapter(listOf())
        recyclerView?.adapter = adapterSubjects

        subjectViewModel.getAll().observe(viewLifecycleOwner, { subjects ->
            if (subjects.isNotEmpty()) {
                adapterSubjects?.apply {
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
            findNavController().navigate(R.id.action_subjectFragment_to_subjectFormFragment)
        }
    }

}