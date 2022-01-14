package com.eduman.ui.dialogs

import android.app.Activity
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.eduman.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ConfirmationDialog(
    private val activity: Activity,
    private val callback: Callback
) {

    interface Callback {
        fun onConfirmed() {}
        fun onCanceled() {}
    }

    private val bottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetDialog)
    private val dialogContent = LayoutInflater.from(activity).inflate(
        R.layout.dialog_confirmation,
        activity.findViewById(android.R.id.content),
        false
    )

    private val textViewTitle: MaterialTextView = dialogContent.findViewById(R.id.dialogConfirmationTitle)
    private val textViewDescription: MaterialTextView = dialogContent.findViewById(R.id.dialogConfirmationDescription)
    private val buttonCancel: MaterialButton = dialogContent.findViewById(R.id.dialogConfirmationButtonCancel)
    private val buttonConfirm: MaterialButton = dialogContent.findViewById(R.id.dialogConfirmationButtonConfirm)

    private var title: String = ""
    private var description: String = ""
    private var buttonCancelText: String = activity.getString(R.string.cancel)
    private var buttonConfirmText: String = activity.getString(R.string.ok)
    @DrawableRes private var buttonCancelIcon: Int = R.drawable.icon_close
    @DrawableRes private var buttonConfirmIcon: Int = R.drawable.icon_check

    fun setTitle(title: String) {
        this.title = title
    }

    fun setTitle(@StringRes titleResId: Int) {
        this.title = activity.getString(titleResId)
    }

    fun setDescription(text: String) {
        this.description = text
    }

    fun setDescription(@StringRes textResId: Int) {
        this.description = activity.getString(textResId)
    }

    fun setButtonCancelText(text: String) {
        this.buttonCancelText = text
    }

    fun setButtonCancelText(@StringRes textResId: Int) {
        this.buttonCancelText = activity.getString(textResId)
    }

    fun setButtonConfirmText(text: String) {
        this.buttonConfirmText = text
    }

    fun setButtonConfirmText(@StringRes textResId: Int) {
        this.buttonConfirmText =  activity.getString(textResId)
    }

    fun setButtonCancelIcon(@DrawableRes drawableResId: Int) {
        this.buttonCancelIcon = drawableResId
    }

    fun setButtonConfirmIcon(@DrawableRes drawableResId: Int) {
        this.buttonConfirmIcon = drawableResId
    }

    fun build(): ConfirmationDialog {
        textViewTitle.text = title
        textViewDescription.text = description
        buttonCancel.apply {
            text = buttonCancelText
            icon = ContextCompat.getDrawable(activity, buttonCancelIcon)
        }
        buttonConfirm.apply {
            text = buttonConfirmText
            icon = ContextCompat.getDrawable(activity, buttonConfirmIcon)
        }

        buttonCancel.setOnClickListener {
            callback.onCanceled()
            dismiss()
        }

        buttonConfirm.setOnClickListener {
            callback.onConfirmed()
            dismiss()
        }

        bottomSheetDialog.setContentView(dialogContent)

        return this
    }

    fun show() = bottomSheetDialog.show()

    fun dismiss() = bottomSheetDialog.dismiss()

}