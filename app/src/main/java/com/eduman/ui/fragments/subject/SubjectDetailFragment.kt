package com.eduman.ui.fragments.subject

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.data.room.entitiy.Grade
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.viewmodel.GradeViewModel
import com.eduman.ui.adapters.GradesAdapter
import com.eduman.ui.dialogs.AddGradeDialog
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectDetailFragment : EduManFragment(
    R.layout.fragment_subject_detail,
    "SubjectDetail"
) {
    private val arguments: SubjectDetailFragmentArgs by navArgs()
    private var subject: Subject? = null

    private val gradeViewModel: GradeViewModel by viewModels()

    private var buttonBack: ImageView? = null
    private var textViewSubjectName: MaterialTextView? = null
    private var textViewTeacherName: MaterialTextView? = null

    private var textViewGradeAverage: MaterialTextView? = null
    private var cardGrades: MaterialCardView? = null
    private var recyclerViewGrades: RecyclerView? = null
    private var cardTests: MaterialCardView? = null

    private var buttonAddGrade: ExtendedFloatingActionButton? = null
    private var buttonAddTest: ExtendedFloatingActionButton? = null

    private var adapterGrades = GradesAdapter(listOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initialize() {
        subject = arguments.subject
        buttonBack = activity?.findViewById(R.id.fragmentSubjectDetailsButtonBack)
        textViewSubjectName = activity?.findViewById(R.id.fragmentSubjectDetailsTextViewSubjectName)
        textViewTeacherName = activity?.findViewById(R.id.fragmentSubjectDetailsTextViewTeacherName)
        textViewGradeAverage = activity?.findViewById(R.id.fragmentSubjectDetailsTextViewGradeAverage)
        cardGrades = activity?.findViewById(R.id.fragmentSubjectDetailsCardGrades)
        recyclerViewGrades = activity?.findViewById(R.id.fragmentSubjectDetailsRecyclerViewGrades)
        cardTests = activity?.findViewById(R.id.fragmentSubjectDetailsCardTests)
        buttonAddGrade = activity?.findViewById(R.id.fragmentSubjectDetailButtonAddGrade)
        buttonAddTest = activity?.findViewById(R.id.fragmentSubjectDetailButtonAddTest)

        textViewSubjectName?.text = subject?.title
        textViewTeacherName?.text = subject?.teacherName

        recyclerViewGrades?.adapter = adapterGrades

        subject?.id?.let { subjectId ->
            gradeViewModel.getAll(subjectId).observe(viewLifecycleOwner, { grades ->
                if (grades.isNotEmpty()) {
                    cardGrades?.visibility = View.VISIBLE
                    adapterGrades.grades = grades
                    adapterGrades.notifyDataSetChanged()

                    var gradeSum = 0.0F
                    var weightSum = 0.0F
                    grades.forEach { grade ->
                        gradeSum += grade.grade * grade.weighting
                        weightSum += grade.weighting
                    }

                    val average = gradeSum / weightSum
                    textViewGradeAverage?.text = String.format("%.2f", average)

                    activity?.let {
                        textViewGradeAverage?.setTextColor(ContextCompat.getColor(it, when(average) {
                            in 1.0F..2.5F -> R.color.success
                            in 2.5F..4.5F -> R.color.warning
                            in 4.5F..6.0F -> R.color.error
                            else -> R.color.black
                        }))
                    }

                } else {
                    cardGrades?.visibility = View.GONE
                }
            })
        }

        buttonBack?.setOnClickListener {
            findNavController().navigateUp()
        }

        buttonAddGrade?.setOnClickListener {
            activity?.let {
                AddGradeDialog(it, object : AddGradeDialog.AddGradeDialogListener {
                    override fun onGradeSet(grade: Grade) {
                        subject?.id?.let { subjectId ->
                            grade.subjectId = subjectId

                            gradeViewModel.insert(grade).invokeOnCompletion {

                            }
                        }
                    }
                }).show()
            }
        }
    }

}