package com.eduman.ui.fragments.subject

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
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
import com.eduman.data.room.entitiy.Test
import com.eduman.data.room.viewmodel.CoreViewModel
import com.eduman.data.room.viewmodel.GradeViewModel
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.adapters.GradesAdapter
import com.eduman.ui.adapters.TestsAdapter
import com.eduman.ui.dialogs.AddGradeDialog
import com.eduman.ui.dialogs.AddTestDialog
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SubjectDetailFragment : EduManFragment(
    R.layout.fragment_subject_detail,
    "SubjectDetail"
) {

    companion object {
        private const val AMOUNT_LAST_GRADES = 3
        private const val AMOUNT_LAST_TESTS = 3
    }

    private val arguments: SubjectDetailFragmentArgs by navArgs()
    private var subject: Subject? = null

    private val gradeViewModel: GradeViewModel by viewModels()
    private val testViewModel: TestViewModel by viewModels()
    private val coreViewModel: CoreViewModel by viewModels()

    private var buttonBack: ImageView? = null
    private var textViewSubjectName: MaterialTextView? = null

    private var textViewTeacherName: MaterialTextView? = null

    private var containerEmpty: LinearLayout? = null

    private var textViewGradeAverage: MaterialTextView? = null
    private var cardGrades: MaterialCardView? = null
    private var recyclerViewGrades: RecyclerView? = null

    private var cardTests: MaterialCardView? = null
    private var recyclerViewTests: RecyclerView? = null

    private var buttonAddGrade: ExtendedFloatingActionButton? = null
    private var buttonAddTest: ExtendedFloatingActionButton? = null

    private var adapterGrades = GradesAdapter(listOf())
    private var adapterTests = TestsAdapter(listOf())

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
        containerEmpty = activity?.findViewById(R.id.fragmentSubjectDetailsContainerEmpty)
        textViewGradeAverage = activity?.findViewById(R.id.fragmentSubjectDetailsTextViewGradeAverage)
        cardGrades = activity?.findViewById(R.id.fragmentSubjectDetailsCardGrades)
        recyclerViewGrades = activity?.findViewById(R.id.fragmentSubjectDetailsRecyclerViewGrades)
        cardTests = activity?.findViewById(R.id.fragmentSubjectDetailsCardTests)
        recyclerViewTests = activity?.findViewById(R.id.fragmentSubjectDetailsRecyclerViewTests)
        buttonAddGrade = activity?.findViewById(R.id.fragmentSubjectDetailButtonAddGrade)
        buttonAddTest = activity?.findViewById(R.id.fragmentSubjectDetailButtonAddTest)

        textViewSubjectName?.text = subject?.title
        textViewTeacherName?.text = subject?.teacherName

        recyclerViewGrades?.adapter = adapterGrades
        recyclerViewTests?.adapter = adapterTests

        subject?.id?.let { subjectId ->
            coreViewModel.checkGradeAndTestCount(subjectId).observe(viewLifecycleOwner, { gradeAndTestCount ->
                if (gradeAndTestCount.gradeCount == 0 && gradeAndTestCount.testCount == 0) {
                    containerEmpty?.visibility = View.VISIBLE
                } else {
                    containerEmpty?.visibility = View.GONE
                }
            })

            gradeViewModel.getLast(subjectId, AMOUNT_LAST_GRADES).observe(viewLifecycleOwner, { grades ->
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

            testViewModel.getNext(subjectId, AMOUNT_LAST_TESTS).observe(viewLifecycleOwner, { tests ->
                if (tests.isNotEmpty()) {
                    cardTests?.visibility = View.VISIBLE
                    adapterTests.tests = tests
                    adapterTests.notifyDataSetChanged()
                } else {
                    cardTests?.visibility = View.GONE
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

                            gradeViewModel.insert(grade)
                        }
                    }
                }).show()
            }
        }

        buttonAddTest?.setOnClickListener {
            activity?.let {
                AddTestDialog(it, object : AddTestDialog.AddTestDialogListener {
                    override fun onSave(topic: String, date: Long) {
                        subject?.id?.let { subjectId ->
                            val test = Test(
                                topic = topic,
                                date = Date(date),
                                subjectId = subjectId
                            )

                            testViewModel.insert(test)
                        }
                    }

                }).show()
            }
        }

    }

}