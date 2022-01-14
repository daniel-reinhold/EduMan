package com.eduman.ui.fragments.test

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eduman.R
import com.eduman.core.Constants.Companion.FORM_MODE_EDIT
import com.eduman.core.Constants.Companion.KEY_FORM_MODE
import com.eduman.core.Constants.Companion.KEY_TEST
import com.eduman.core.EduManFragment
import com.eduman.core.util.GradeUtil
import com.eduman.core.util.extensions.format
import com.eduman.core.util.extensions.setTextColorByResId
import com.eduman.core.util.formatter.DateTimeFormatter
import com.eduman.data.room.entity.Grade
import com.eduman.data.room.entity.Test
import com.eduman.data.room.entity.relation.TestAndGrade
import com.eduman.data.room.viewmodel.GradeViewModel
import com.eduman.data.room.viewmodel.SubjectViewModel
import com.eduman.data.room.viewmodel.TestViewModel
import com.eduman.ui.dialogs.AddGradeDialog
import com.eduman.ui.dialogs.ConfirmationDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestDetailFragment : EduManFragment("TestDetailFragment") {

    private val subjectViewModel: SubjectViewModel by viewModels()
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

    private var confirmDeleteCallback = object : ConfirmationDialog.Callback {
        override fun onConfirmed() {
            test?.let {
                testViewModel.delete(it).invokeOnCompletion {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
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

        test?.subjectId?.let { subjectId ->
            subjectViewModel.find(subjectId).observe(viewLifecycleOwner, { subject ->
                setActionBarTitle(R.string.test)
                setActionBarSubTitle(subject?.title)
            })
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
                        this.text = grade.format()
                        this.setTextColorByResId(GradeUtil.gradeToColor(grade))
                    }
                    textViewWeighting?.text = testAndGrade.grade.weighting.format()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.test_detail_overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.testDetailMenuItemEdit -> {
                findNavController().navigate(
                    R.id.action_global_testFormFragment,
                    bundleOf(
                        KEY_FORM_MODE to FORM_MODE_EDIT,
                        KEY_TEST to test
                    )
                )
                true
            }
            R.id.testDetailMenuItemDelete -> {
                activity?.let {
                    ConfirmationDialog(it, confirmDeleteCallback).apply {
                        setTitle(R.string.title_dialog_delete_test)
                        setDescription(getString(R.string.description_dialog_delete_test, test?.topic))
                        setButtonConfirmText(R.string.delete)
                        setButtonConfirmIcon(R.drawable.icon_delete)
                    }.build().show()
                    true
                }
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}