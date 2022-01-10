package com.eduman.ui.fragments.grade

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
import com.eduman.data.room.viewmodel.GradeViewModel
import com.eduman.ui.adapters.recyclerview.GradesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GradesFragment : EduManFragment("GradesFragment") {

    // <editor-fold desc="Private variables" defaultstate="collapsed">

    private var recyclerView: RecyclerView? = null
    private val adapter = GradesAdapter()

    private var subject: Subject? = null
    private val gradeViewModel: GradeViewModel by viewModels()

    // </editor-fold>

    // <editor-fold desc="Lifecycle methods" defaultstate="collapsed">

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_grades, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    // </editor-fold>

    // <editor-fold desc="Initialization methods" defaultstate="collapsed">

    private fun initialize() {
        subject = arguments?.getParcelable(KEY_SUBJECT)
        recyclerView = activity?.findViewById(R.id.fragmentGradesRecyclerView)

        recyclerView?.adapter = adapter

        setActionBarTitle(getString(R.string.grades))
        setActionBarSubTitle(subject?.title)

        subject?.id?.let { subjectId ->
            gradeViewModel.getAll(subjectId).observe(viewLifecycleOwner, { grades ->
                adapter.updateList(grades)
            })
        }
    }

    // </editor-fold>

}