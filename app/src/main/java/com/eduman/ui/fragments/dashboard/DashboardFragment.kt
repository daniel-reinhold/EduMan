package com.eduman.ui.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_TEST
import com.eduman.core.EduManFragment
import com.eduman.core.util.SharedPreferencesUtil
import com.eduman.core.util.extensions.getHour
import com.eduman.data.room.entity.relation.TestAndSubject
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.adapters.recyclerview.UpcomingTestsAdapter
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DashboardFragment : EduManFragment("DashboardFragment") {

    companion object {
        private const val DEFAULT_UPCOMING_TESTS_COUNT = 3
    }

    private val testViewModel: TestViewModel by viewModels()
    private var sharedPreferences: SharedPreferencesUtil? = null

    private var textViewGreeting: MaterialTextView? = null
    private var imageViewGreeting: ImageView? = null

    private var recyclerViewUpcomingTests: RecyclerView? = null
    private var containerNoUpcomingTests: LinearLayout? = null
    private val upcomingTestsAdapterCallback = object : UpcomingTestsAdapter.Callback {
        override fun onClick(testAndSubject: TestAndSubject) {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_testDetailFragment2,
                bundleOf(KEY_TEST to testAndSubject.test)
            )
        }
    }
    private val upcomingTestsAdapter = UpcomingTestsAdapter(upcomingTestsAdapterCallback)

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
        sharedPreferences = SharedPreferencesUtil(activity)
        recyclerViewUpcomingTests = view.findViewById(R.id.fragmentDashboardRecyclerViewUpcomingTests)
        containerNoUpcomingTests = view.findViewById(R.id.fragmentDashboardContainerNoUpcomingTests)
        textViewGreeting = view.findViewById(R.id.fragmentDashboardTextViewGreeting)
        imageViewGreeting = view.findViewById(R.id.fragmentDashboardImageViewGreeting)

        var greetingIconResId = 0
        var greetingTextResId = 0

        when (Date().getHour()) {
            in 0..9 -> {
                greetingTextResId = R.string.greeting_morning
                greetingIconResId = R.drawable.icon_morning
            }
            in 10..17 -> {
                greetingTextResId = R.string.greeting_day
                greetingIconResId = R.drawable.icon_day
            }
            in 18..23 -> {
                greetingTextResId = R.string.greeting_evening
                greetingIconResId = R.drawable.icon_evening
            }
            else -> {
                greetingTextResId = R.string.greeting_day
                greetingIconResId = R.drawable.icon_day
            }
        }

        textViewGreeting?.text = activity?.getString(greetingTextResId, sharedPreferences?.getUsername())
        activity?.let {
            imageViewGreeting?.setImageDrawable(
                ContextCompat.getDrawable(it, greetingIconResId)
            )
        }

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