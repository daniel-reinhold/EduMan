package com.eduman.ui.fragments.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_SUBJECT
import com.eduman.core.EduManFragment
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.adapters.TestsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestsFragment : EduManFragment("TestsFragment") {

    // <editor-fold desc="Private variables" defaultstate="collapsed">

    private var subject: Subject? = null
    private val testViewModel: TestViewModel by viewModels()

    private var recyclerView: RecyclerView? = null
    private var adapter = TestsAdapter(listOf())

    // </editor-fold>

    // <editor-fold desc="Lifecycle methods" defaultstate="collapsed">

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    // </editor-fold>

    // <editor-fold desc="Initialization methods" defaultstate="collapsed">

    private fun initialize() {
        subject = arguments?.getParcelable(KEY_SUBJECT)
        recyclerView = activity?.findViewById(R.id.fragmentTestsRecyclerView)

        recyclerView?.adapter = adapter

        setActionBarTitle(getString(R.string.tests))
        setActionBarSubTitle(subject?.title)

        subject?.id?.let { subjectId ->
            testViewModel.getAll(subjectId).observe(viewLifecycleOwner, { tests ->
                adapter.updateList(tests)
            })
        }
    }

    // </editor-fold>

}