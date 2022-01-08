package com.eduman.ui.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.adapters.UpcomingTestsAdapter
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : EduManFragment("EduManFragment") {

    companion object {
        private const val DEFAULT_UPCOMING_TESTS_COUNT = 3
    }

    private var recyclerViewUpcomingTests: RecyclerView? = null
    private var containerNoUpcomingTests: LinearLayout? = null
    private val upcomingTestsAdapter = UpcomingTestsAdapter()

    private val testViewModel: TestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        initialize(view)

        return view
    }

    private fun initialize(view: View) {
        recyclerViewUpcomingTests = view.findViewById(R.id.fragmentDashboardRecyclerViewUpcomingTests)
        containerNoUpcomingTests = view.findViewById(R.id.fragmentDashboardContainerNoUpcomingTests)

        recyclerViewUpcomingTests?.adapter = upcomingTestsAdapter

        testViewModel.getNext(DEFAULT_UPCOMING_TESTS_COUNT).observe(viewLifecycleOwner, { list ->
            if (list.isNotEmpty()) {
                recyclerViewUpcomingTests?.visibility = View.VISIBLE
                containerNoUpcomingTests?.visibility = View.GONE
                upcomingTestsAdapter.updateList(list)
            } else {
                containerNoUpcomingTests?.visibility = View.VISIBLE
                recyclerViewUpcomingTests?.visibility = View.GONE
            }
        })
    }

}