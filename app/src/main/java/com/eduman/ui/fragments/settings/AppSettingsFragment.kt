package com.eduman.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.eduman.R
import com.eduman.core.EduManFragment
import com.eduman.core.components.textfield.BaseTextField
import com.eduman.core.components.textfield.IntegerTextField
import com.eduman.core.components.textfield.validator.implementation.PresenceValidator
import com.eduman.core.util.SharedPreferencesUtil
import com.eduman.core.util.extensions.orFalse
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textview.MaterialTextView

class AppSettingsFragment : EduManFragment("AppSettingsFragment") {

    private var sharedPreferences: SharedPreferencesUtil? = null

    private var switchUsePin: SwitchMaterial? = null

    private var containerLessonDuration: LinearLayout? = null
    private var textViewLessonDuration: MaterialTextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        sharedPreferences = SharedPreferencesUtil(activity)
        switchUsePin = activity?.findViewById(R.id.fragmentAppSettingsSwitchUsePin)
        containerLessonDuration = activity?.findViewById(R.id.fragmentAppSettingsContainerLessonDuration)
        textViewLessonDuration = activity?.findViewById(R.id.fragmentAppSettingsTextViewLessonDuration)

        switchUsePin?.isChecked = sharedPreferences?.usePin().orFalse()
        textViewLessonDuration?.text = activity?.getString(R.string.duration_lesson_value, sharedPreferences?.getLessonDuration())

        switchUsePin?.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences?.setUsePin(isChecked)
        }

        containerLessonDuration?.setOnClickListener {
            activity?.let {
                val bottomSheetDialog = BottomSheetDialog(it, R.style.BottomSheetDialog)
                val dialogContent = LayoutInflater.from(it).inflate(
                    R.layout.dialog_set_lesson_duration,
                    it.findViewById(android.R.id.content),
                    false
                )

                val textFieldLessonDuration: IntegerTextField = dialogContent.findViewById(R.id.dialogSetLessonDurationTextFieldDuration)
                val buttonSave: MaterialButton = dialogContent.findViewById(R.id.dialogSetLessonDurationButtonSave)

                textFieldLessonDuration.addValidator(PresenceValidator(it))

                sharedPreferences?.getLessonDuration()?.let { lessonDuration ->
                    textFieldLessonDuration.setValue(lessonDuration)
                }

                textFieldLessonDuration.setOnTextChangeListener(object : BaseTextField.TextChangeListener {
                    override fun onTextChange(text: String) {
                        buttonSave.isEnabled = textFieldLessonDuration.getErrorCount() == 0
                    }
                })

                buttonSave.setOnClickListener {
                    val newLessonDuration = textFieldLessonDuration.getValue()

                    sharedPreferences?.setLessonDuration(textFieldLessonDuration.getValue())
                    textViewLessonDuration?.text = activity?.getString(R.string.duration_lesson_value, newLessonDuration)
                    bottomSheetDialog.dismiss()
                }

                bottomSheetDialog.setContentView(dialogContent)
                bottomSheetDialog.show()
            }
        }

    }

}