package com.eduman.ui.fragments.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_SUBJECT
import com.eduman.core.Constants.Companion.KEY_TEST
import com.eduman.core.Constants.Companion.KEY_TEST_AND_GRADE
import com.eduman.core.EduManFragment
import com.eduman.data.room.entity.Subject
import com.eduman.data.room.entity.Test
import com.eduman.data.room.entity.relation.TestAndGrade
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.adapters.recyclerview.TestsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestsFragment : EduManFragment("TestsFragment") {

    // <editor-fold desc="Private variables" defaultstate="collapsed">

    private var subject: Subject? = null
    private val testViewModel: TestViewModel by viewModels()

    private var recyclerView: RecyclerView? = null
    private var adapter = TestsAdapter(object : TestsAdapter.Callback {
        override fun onTestClicked(test: Test) {
            findNavController().navigate(
                R.id.action_testsFragment_to_testDetailFragment,
                bundleOf(KEY_TEST to test)
            )
        }

    })

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

        setActionBarTitle(getString(R.string.tests))
        setActionBarSubTitle(subject?.title)

        recyclerView?.adapter = adapter

        subject?.id?.let { subjectId ->
            testViewModel.getAll(subjectId).observe(viewLifecycleOwner, { tests ->
                adapter.updateList(tests)
            })
        }
    }

    // </editor-fold>

}