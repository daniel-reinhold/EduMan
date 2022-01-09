package com.eduman.ui.activities

import android.os.Bundle
import com.eduman.R
import com.eduman.core.EduManActivity

class StartupActivity : EduManActivity("StartupActivity") {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        initialize()
    }

    private fun initialize() {

    }

}