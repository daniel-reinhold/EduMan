package com.eduman.ui.activites

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eduman.R
import com.eduman.core.EduManActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : EduManActivity("MainActivity") {

    private var bottomNavigationMenu: BottomNavigationView? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        initializeNavigation()
    }

    private fun initialize() {
    }

    private fun initializeNavigation() {
        bottomNavigationMenu = findViewById(R.id.activityMainBottomNavigationMenu)
        navController = findNavController(R.id.activityMainHostFragment)

        navController?.let { controller ->
            bottomNavigationMenu?.setupWithNavController(controller)
        }
    }


}