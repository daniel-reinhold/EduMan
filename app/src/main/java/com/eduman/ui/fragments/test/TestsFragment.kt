package com.eduman.ui.fragments.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_SUBJECT
import com.eduman.core.EduManFragment
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.adapters.TestsAdapter
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestsFragment : Fragment(R.layout.fragment_tests) {

    private var subject: Subject? = null
    private val testViewModel: TestViewModel by viewModels()

    private var recyclerView: RecyclerView? = null
    private var adapter = TestsAdapter(listOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        subject = arguments?.getParcelable(KEY_SUBJECT)
        recyclerView = activity?.findViewById(R.id.fragmentTestsRecyclerView)

        //title?.text = activity?.getString(R.string.title_tests, subject?.title)

        recyclerView?.adapter = adapter

        subject?.id?.let { subjectId ->
            testViewModel.getAll(subjectId).observe(viewLifecycleOwner, { tests ->
                adapter.apply {
                    this.tests = tests
                    this.notifyDataSetChanged()
                }
            })
        }
    }

}