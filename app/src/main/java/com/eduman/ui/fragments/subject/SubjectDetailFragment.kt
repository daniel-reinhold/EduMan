package com.eduman.ui.fragments.subject

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.App
import com.eduman.core.Constants.Companion.KEY_SUBJECT
import com.eduman.core.EduManFragment
import com.eduman.core.util.GradeUtil
import com.eduman.data.room.entitiy.Grade
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.entitiy.Test
import com.eduman.data.room.viewmodel.CoreViewModel
import com.eduman.data.room.viewmodel.GradeViewModel
import com.eduman.data.room.viewmodel.SubjectViewModel
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.adapters.GradesPreviewAdapter
import com.eduman.ui.adapters.TestsPreviewAdapter
import com.eduman.ui.dialogs.AddGradeDialog
import com.eduman.ui.dialogs.AddTestDialog
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SubjectDetailFragment : EduManFragment("Dashboard") {

    // <editor-fold desc="Static variables" defaultstate="collapsed">

    companion object {
        private const val AMOUNT_LAST_GRADES = 3
        private const val AMOUNT_LAST_TESTS = 3
    }

    // </editor-fold>

    // <editor-fold desc="Private variables" defaultstate="collapsed">

    private var subject: Subject? = null

    private val subjectViewModel: SubjectViewModel by viewModels()
    private val gradeViewModel: GradeViewModel by viewModels()
    private val testViewModel: TestViewModel by viewModels()
    private val coreViewModel: CoreViewModel by viewModels()

    private var buttonGrades: ImageView? = null
    private var buttonTests: ImageView? = null

    private var textViewTeacherName: MaterialTextView? = null

    private var containerEmpty: LinearLayout? = null

    private var textViewGradeAverage: MaterialTextView? = null
    private var cardGrades: MaterialCardView? = null
    private var recyclerViewGrades: RecyclerView? = null

    private var cardTests: MaterialCardView? = null
    private var recyclerViewTests: RecyclerView? = null

    private var buttonAddGrade: ExtendedFloatingActionButton? = null
    private var buttonAddTest: ExtendedFloatingActionButton? = null

    private var adapterGrades = GradesPreviewAdapter(listOf())
    private var adapterTests = TestsPreviewAdapter(listOf())

    // </editor-fold>

    // <editor-fold desc="Lifecycle methods" defaultstate="collapsed">

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subject_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        initialize()
    }

    // </editor-fold>

    // <editor-fold desc="Initialization methods" defaultstate="collapsed">

    private fun initialize() {
        subject = arguments?.getParcelable(KEY_SUBJECT)
        buttonGrades = activity?.findViewById(R.id.fragmentSubjectDetailsButtonGrades)
        buttonTests = activity?.findViewById(R.id.fragmentSubjectDetailsButtonTests)
        textViewTeacherName = activity?.findViewById(R.id.fragmentSubjectDetailsTextViewTeacherName)
        containerEmpty = activity?.findViewById(R.id.fragmentSubjectDetailsContainerEmpty)
        textViewGradeAverage = activity?.findViewById(R.id.fragmentSubjectDetailsTextViewGradeAverage)
        cardGrades = activity?.findViewById(R.id.fragmentSubjectDetailsCardGrades)
        recyclerViewGrades = activity?.findViewById(R.id.fragmentSubjectDetailsRecyclerViewGrades)
        cardTests = activity?.findViewById(R.id.fragmentSubjectDetailsCardTests)
        recyclerViewTests = activity?.findViewById(R.id.fragmentSubjectDetailsRecyclerViewTests)
        buttonAddGrade = activity?.findViewById(R.id.fragmentSubjectDetailButtonAddGrade)
        buttonAddTest = activity?.findViewById(R.id.fragmentSubjectDetailButtonAddTest)

        recyclerViewGrades?.adapter = adapterGrades
        recyclerViewTests?.adapter = adapterTests

        subject?.id?.let { subjectId ->
            subjectViewModel.find(subjectId).observe(viewLifecycleOwner, {
                setActionBarTitle(it?.title)
                textViewTeacherName?.text = it?.teacherName
            })

            // Check if any grades or tests exist for the subject
            coreViewModel.checkGradeAndTestCount(subjectId).observe(viewLifecycleOwner, { gradeAndTestCount ->
                if (gradeAndTestCount.gradeCount == 0 && gradeAndTestCount.testCount == 0) {
                    containerEmpty?.visibility = View.VISIBLE
                } else {
                    containerEmpty?.visibility = View.GONE
                }
            })

            gradeViewModel.getAll(subjectId).observe(viewLifecycleOwner, { grades ->
                textViewGradeAverage?.apply {
                    val average = GradeUtil.calculateGradeAverage(grades)

                    this.text = GradeUtil.formatGrade(average, 2)
                    activity?.let {
                        this.setTextColor(
                            ContextCompat.getColor(it, GradeUtil.gradeToColor(average))
                        )
                    }
                }
            })

            // Display the last added grades
            gradeViewModel.getLast(subjectId, AMOUNT_LAST_GRADES).observe(viewLifecycleOwner, { grades ->
                if (grades.isNotEmpty()) {
                    cardGrades?.visibility = View.VISIBLE
                    adapterGrades.updateList(grades)
                } else {
                    cardGrades?.visibility = View.GONE
                }
            })

            // Display the next tests
            testViewModel.getNext(subjectId, AMOUNT_LAST_TESTS).observe(viewLifecycleOwner, { tests ->
                if (tests.isNotEmpty()) {
                    cardTests?.visibility = View.VISIBLE
                    adapterTests.updateList(tests)
                } else {
                    cardTests?.visibility = View.GONE
                }
            })

        }

        // Navigate to the grades fragment
        buttonGrades?.setOnClickListener {
            if (App.IS_RELEASE) {
                showInterstitialAd(object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        navigateToGradesFragment()
                    }

                    override fun onAdFailedToShowFullScreenContent(error: AdError) {
                        navigateToGradesFragment()
                    }
                })
            } else {
                navigateToGradesFragment()
            }
        }

        // Navigate to the tests fragment
        buttonTests?.setOnClickListener {
            if (App.IS_RELEASE) {
                showInterstitialAd(object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        navigateToTestsFragment()
                    }

                    override fun onAdFailedToShowFullScreenContent(error: AdError) {
                        navigateToTestsFragment()
                    }
                })
            } else {
                navigateToTestsFragment()
            }
        }

        // Display a dialog to add a grade
        buttonAddGrade?.setOnClickListener {
            activity?.let {
                AddGradeDialog(it, object : AddGradeDialog.AddGradeDialogListener {
                    override fun onSave(grade: Float, weighting: Float) {
                        subject?.id?.let { subjectId ->
                            val gradeInstance = Grade(
                                grade = grade,
                                weighting = weighting,
                                subjectId = subjectId
                            )

                            gradeViewModel.insert(gradeInstance)
                        }
                    }
                }).show()
            }
        }

        // Display a dialog to add a test
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

    // </editor-fold>

    // <editor-fold desc="Initializing Options menu" defaultstate="collapsed">

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.subject_detail_overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.subjectDetailMenuItemEdit -> {
                subject?.let {
                    findNavController().navigate(
                        R.id.action_subjectDetailFragment_to_subjectFormFragment,
                        bundleOf(KEY_SUBJECT to it)
                    )
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // </editor-fold>

    // <editor-fold desc="Navigation methods" defaultstate="collapsed">

    private fun navigateToGradesFragment() {
        subject?.let {
            findNavController().navigate(
                R.id.action_subjectDetailFragment_to_gradesFragment,
                bundleOf(KEY_SUBJECT to it)
            )
        }
    }

    private fun navigateToTestsFragment() {
        subject?.let {
            findNavController().navigate(
                R.id.action_subjectDetailFragment_to_testsFragment,
                bundleOf(KEY_SUBJECT to it)
            )
        }
    }

    // </editor-fold>

}