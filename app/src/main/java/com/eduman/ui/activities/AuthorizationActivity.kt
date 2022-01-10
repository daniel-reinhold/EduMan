package com.eduman.ui.activities

import android.os.Bundle
import android.widget.EditText
import com.eduman.R
import com.eduman.core.EduManActivity
import com.google.android.material.card.MaterialCardView

class AuthorizationActivity : EduManActivity("AuthorizationActivity") {

    private var cardDigit1: MaterialCardView? = null
    private var cardDigit2: MaterialCardView? = null
    private var cardDigit3: MaterialCardView? = null
    private var cardDigit4: MaterialCardView? = null

    private var textFieldDigit1: EditText? = null
    private var textFieldDigit2: EditText? = null
    private var textFieldDigit3: EditText? = null
    private var textFieldDigit4: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
    }

    private fun initialize() {
        cardDigit1 = findViewById(R.id.activityAuthorizationCardDigit1)
        cardDigit2 = findViewById(R.id.activityAuthorizationCardDigit2)
        cardDigit3 = findViewById(R.id.activityAuthorizationCardDigit3)
        cardDigit4 = findViewById(R.id.activityAuthorizationCardDigit4)

        textFieldDigit1 = findViewById(R.id.activityAuthorizationDigit1)
        textFieldDigit2 = findViewById(R.id.activityAuthorizationDigit2)
        textFieldDigit3 = findViewById(R.id.activityAuthorizationDigit3)
        textFieldDigit4 = findViewById(R.id.activityAuthorizationDigit4)
    }

}