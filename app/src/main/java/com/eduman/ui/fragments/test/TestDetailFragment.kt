package com.eduman.ui.fragments.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_TEST
import com.eduman.core.Constants.Companion.KEY_TEST_AND_GRADE
import com.eduman.core.EduManFragment
import com.eduman.core.util.GradeUtil
import com.eduman.core.util.extensions.setTextColorByResId
import com.eduman.core.util.formatter.DateTimeFormatter
import com.eduman.data.room.entity.Grade
import com.eduman.data.room.entity.Test
import com.eduman.data.room.entity.relation.TestAndGrade
import com.eduman.data.room.viewmodel.GradeViewModel
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.dialogs.AddGradeDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestDetailFragment : EduManFragment("TestDetailFragment") {

    private val testViewModel: TestViewModel by viewModels()
    private val gradeViewModel: GradeViewModel by viewModels()
    private var test: Test? = null

    private var textViewTopic: MaterialTextView? = null
    private var textViewDate: MaterialTextView? = null
    private var textViewAdditionalDateInfo: MaterialTextView? = null

    private var containerGrade: LinearLayout? = null
    private var textViewGrade: MaterialTextView? = null
    private var textViewWeighting: MaterialTextView? = null

    private var containerNoGrade: LinearLayout? = null
    private var buttonRecordGrade: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        test = arguments?.getParcelable(KEY_TEST)
        textViewTopic = activity?.findViewById(R.id.fragmentTestDetailTextViewTopic)
        textViewDate = activity?.findViewById(R.id.fragmentTestDetailTextViewDate)
        textViewAdditionalDateInfo = activity?.findViewById(R.id.fragmentTestDetailTextViewTestAdditionalDateInfo)
        containerGrade = activity?.findViewById(R.id.fragmentTestDetailContainerGrade)
        textViewGrade = activity?.findViewById(R.id.fragmentTestDetailTextViewGrade)
        textViewWeighting = activity?.findViewById(R.id.fragmentTestDetailTextViewWeighting)
        containerNoGrade = activity?.findViewById(R.id.fragmentTestDetailContainerNoGrade)
        buttonRecordGrade = activity?.findViewById(R.id.fragmentTestDetailButtonRecordGrade)

        textViewTopic?.text = test?.topic
        test?.date?.let {
            textViewDate?.text = DateTimeFormatter.formatDateTimeDefault(activity, it)
        }

        test?.id?.let { testId ->
            testViewModel.getGrade(testId).observe(viewLifecycleOwner, { testAndGrade ->
                if (testAndGrade?.grade == null) {
                    // No grade has been recorded
                    containerNoGrade?.visibility = View.VISIBLE
                    containerGrade?.visibility = View.GONE
                } else {
                    // Grade has been recorded
                    containerGrade?.visibility = View.VISIBLE
                    containerNoGrade?.visibility = View.GONE
                    textViewGrade?.apply {
                        val grade = testAndGrade.grade.grade
                        this.text = GradeUtil.formatGrade(grade)
                        this.setTextColorByResId(GradeUtil.gradeToColor(grade))
                    }
                    textViewWeighting?.text = GradeUtil.formatGrade(testAndGrade.grade.weighting)
                }
            })

            buttonRecordGrade?.setOnClickListener {
                activity?.let {
                    test?.subjectId?.let { subjectId ->
                        AddGradeDialog(it, object : AddGradeDialog.AddGradeDialogListener {
                            override fun onSave(grade: Float, weighting: Float) {
                                val associatedGrade = Grade(
                                    grade = grade,
                                    weighting = weighting,
                                    subjectId = subjectId,
                                    testId = testId
                                )

                                gradeViewModel.insert(associatedGrade)
                            }
                        }).show()
                    }
                }
            }
        }
    }

}