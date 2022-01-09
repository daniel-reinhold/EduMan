package com.eduman.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.eduman.core.EduManActivity
import com.eduman.data.room.viewmodel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : EduManActivity("LaunchActivity") {

    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
    }

    private fun initialize() {
        settingViewModel.getAllSettings().observe(this, {
            if (it.userName == null || it.usePin == null) {
                startActivity(Intent(this, StartupActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        })
    }

}