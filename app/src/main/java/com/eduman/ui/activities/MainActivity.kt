package com.eduman.ui.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eduman.R
import com.eduman.core.EduManActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : EduManActivity("MainActivity") {

    // <editor-fold desc="Static variables" defaultstate="collapsed"

    companion object {
        // A list of fragments which should provide a "back-button"
        private val CHILD_FRAGMENTS = listOf(
            R.id.subjectFormFragment,
            R.id.subjectDetailFragment,
            R.id.testsFragment,
            R.id.gradesFragment
        )
    }

    // </editor-fold>

    // <editor-fold desc="Private variables" defaultstate="collapsed">

    private var navHostFragment: NavHostFragment? = null
    private var bottomNavigationMenu: BottomNavigationView? = null

    private var navController: NavController? = null
    private var appBarConfiguration: AppBarConfiguration? = null

    private var toolbar: Toolbar? = null

    private var adView: AdView? = null

    // </editor-fold>

    // <editor-fold desc="Lifecycle methods" defaultstate="collapsed">

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeAppBar()
        initializeNavigation()
        initializeAds()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.let { controller ->
            appBarConfiguration?.let { config ->
                controller.navigateUp(config)
            }
        } ?: true
    }

    // </editor-fold>

    // <editor-fold desc="Initialization methods" defaultstate="collapsed">

    /**
     * This method initializes the Navigation component
     */
    private fun initializeNavigation() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.activityMainHostFragment) as NavHostFragment
        bottomNavigationMenu = findViewById(R.id.activityMainBottomNavigationMenu)
        navController = navHostFragment?.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dashboardFragment, R.id.subjectsFragment)
        )

        navController?.let { controller ->
            bottomNavigationMenu?.setupWithNavController(controller)

            appBarConfiguration?.let { config ->
                setupActionBarWithNavController(controller, config)
            }
        }

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled((destination.id in CHILD_FRAGMENTS))
                setHomeAsUpIndicator(if (destination.id in CHILD_FRAGMENTS) R.drawable.icon_arrow_left else 0)
                setActionBarSubTitle("")
            }
        }
    }

    /**
     * This method initializes the AppBar
     */
    private fun initializeAppBar() {
        toolbar = findViewById(R.id.activityMainToolBar)

        setSupportActionBar(toolbar)
    }

    /**
     * This method initializes the Ads
     */
    private fun initializeAds() {
        adView = findViewById(R.id.activityMainAdView)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
    }

    // </editor-fold>

}