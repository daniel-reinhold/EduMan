package com.eduman.ui.dialogs

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import com.eduman.R
import com.eduman.core.util.GeneralUtil
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.slider.AlphaSlider
import com.flask.colorpicker.slider.LightnessSlider
import com.google.android.material.bottomsheet.BottomSheetDialog

class ColorPickerDialog(
    activity: Activity,
    callback: ColorPickerDialogListener
) {

    interface ColorPickerDialogListener {
        fun onColorSelected(color: Int)
    }

    companion object {
        const val INITIAL_COLOR: Long = 0xFF34C759
    }

    private val dialogContent = LayoutInflater.from(activity).inflate(
        R.layout.dialog_select_color,
        activity.findViewById(android.R.id.content),
        false
    )
    private val bottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetDialog)

    private val colorPicker: ColorPickerView = dialogContent.findViewById(R.id.dialogSelectColorColorPicker)
    private val colorView: View = dialogContent.findViewById(R.id.dialogSelectColorColorView)
    private val alphaSlider: AlphaSlider = dialogContent.findViewById(R.id.dialogSelectColorAlphaSlider)
    private val lightnessSlider: LightnessSlider = dialogContent.findViewById(R.id.dialogSelectColorLightnessSlider)

    init {
        bottomSheetDialog.setContentView(dialogContent)

        colorPicker.setInitialColor(INITIAL_COLOR.toInt(), true)

        colorPicker.addOnColorChangedListener { color ->
            colorView.backgroundTintList = GeneralUtil.getColorStateList(color)
        }

        bottomSheetDialog.setOnDismissListener {
            callback.onColorSelected(colorPicker.selectedColor)
        }
    }

    fun show() = bottomSheetDialog.show()

    fun dismiss() = bottomSheetDialog.dismiss()

}