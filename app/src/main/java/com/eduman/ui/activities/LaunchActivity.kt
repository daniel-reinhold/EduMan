package com.eduman.ui.activities

import android.content.Intent
import android.os.Bundle
import com.eduman.core.EduManActivity
import com.eduman.core.util.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint

class LaunchActivity : EduManActivity("LaunchActivity") {

    private var sharedPreferences: SharedPreferencesUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
    }

    private fun initialize() {
        sharedPreferences = SharedPreferencesUtil(this)

        if (sharedPreferences?.getUsername() == null || sharedPreferences?.usePin() == null) {
            startActivity(Intent(this, SetupActivity::class.java))
        } else {
            if (sharedPreferences?.usePin() == true) {
                startActivity(Intent(this, AuthorizationActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

}