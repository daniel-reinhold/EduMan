package com.eduman.ui.fragments.timetable

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.ui.adapters.recyclerview.TimetableDayAdapter

class TimetableFragment : EduManFragment("TimetableFragment") {

    data class Day(var id: Int, var subjects: List<Subject>)
    data class Subject(var subjectName: String, var roomName: String)
    val data = listOf(
        Day(
            1,
            listOf(
                Subject("Day1", "1"),
                Subject("Day1", "2"),
                Subject("Day1", "3"),
                Subject("Day1", "4"),
                Subject("Day1", "5"),
                Subject("Day1", "6"),
                Subject("Day1", "7"),
                Subject("Day1", "8"),
            )
        ),
        Day(
            2,
            listOf(
                Subject("Day2", "1"),
                Subject("Day2", "2"),
                Subject("Day2", "3"),
                Subject("Day2", "4"),
                Subject("Day2", "5"),
                Subject("Day2", "6"),
                Subject("Day2", "7"),
                Subject("Day2", "8"),
            )
        ),
        Day(
            3,
            listOf(
                Subject("Day3", "1"),
                Subject("Day3", "2"),
                Subject("Day3", "3"),
                Subject("Day3", "4"),
                Subject("Day3", "5"),
                Subject("Day3", "6"),
                Subject("Day3", "7"),
                Subject("Day3", "8"),
            )
        ),
        Day(
            4,
            listOf(
                Subject("Day4", "1"),
                Subject("Day4", "2"),
                Subject("Day4", "3"),
                Subject("Day4", "4"),
                Subject("Day4", "5"),
                Subject("Day4", "6"),
                Subject("Day4", "7"),
                Subject("Day4", "8"),
            )
        ),
        Day(
            5,
            listOf(
                Subject("Day5", "1"),
                Subject("Day5", "2"),
                Subject("Day5", "3"),
                Subject("Day5", "4"),
                Subject("Day5", "5"),
                Subject("Day5", "6"),
                Subject("Day5", "7"),
                Subject("Day5", "8"),
            )
        ),
    )

    companion object {
        const val DAYS_SHOWING = 3

        const val MONDAY    = 0
        const val TUESDAY   = 1
        const val WEDNESDAY = 2
        const val THURSDAY  = 3
        const val FRIDAY    = 4
    }

    private var buttonBack: ImageView? = null
    private var buttonNext: ImageView? = null

    private var recyclerView: RecyclerView? = null
    private var adapter = TimetableDayAdapter()

    private var beginningDay = MONDAY

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timetable, container, false)

        initialize(view)

        return view
    }

    private fun initialize(view: View) {
        setActionBarTitle(R.string.timetable)
        setHasOptionsMenu(true)

        recyclerView = view.findViewById(R.id.fragmentTimetableRecyclerView)
        buttonBack = view.findViewById(R.id.fragmentTimetableButtonBack)
        buttonNext = view.findViewById(R.id.fragmentTimetableButtonNext)

        buttonBack?.setOnClickListener {
            if (beginningDay >= TUESDAY) {
                beginningDay--
                updateList()
            }
        }

        buttonNext?.setOnClickListener {
            if ((beginningDay + DAYS_SHOWING) < data.size) {
                beginningDay++
                updateList()
            }
        }

        recyclerView?.adapter = adapter
        updateList()
    }

    private fun updateList() {
        adapter.updateList(data.subList(beginningDay, beginningDay + DAYS_SHOWING))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_timetable_fragment, menu)
    }

}