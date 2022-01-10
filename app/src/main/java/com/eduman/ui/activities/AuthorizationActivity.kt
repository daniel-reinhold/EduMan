package com.eduman.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import com.eduman.R
import com.eduman.core.Constants.Companion.DEFAULT_PIN_LENGTH
import com.eduman.core.EduManActivity
import com.eduman.core.util.SharedPreferencesUtil
import com.google.android.material.textfield.TextInputLayout
class AuthorizationActivity : EduManActivity("AuthorizationActivity") {

    private var sharedPreferences: SharedPreferencesUtil? = null

    private var toolbar: Toolbar? = null
    private var textFieldPin: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        initialize()
    }

    private fun initialize() {
        sharedPreferences = SharedPreferencesUtil(this)
        toolbar = findViewById(R.id.activityAuthorizationToolBar)
        textFieldPin = findViewById(R.id.activityAuthorizationTextFieldPin)

        setSupportActionBar(toolbar)

        textFieldPin?.editText?.doAfterTextChanged {
            val pin = it.toString()

            textFieldPin?.apply {
                this.isErrorEnabled = false
                this.error = null
            }

            if (pin.length == DEFAULT_PIN_LENGTH) {
                if (sharedPreferences?.getPin() == pin) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    textFieldPin?.apply {
                        this.isErrorEnabled = true
                        this.error = getString(R.string.error_wrong_pin)
                    }
                }
            }
        }
    }

}