package com.eduman.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.eduman.R
import com.eduman.core.EduManActivity
import com.eduman.ui.adapters.viewpager.SetupViewPagerAdapter
import com.google.android.material.button.MaterialButton

class SetupActivity : EduManActivity("StartupActivity") {

    companion object {
        const val STEPS: Int = 4
        const val POSITION_SETUP_START = 0
        const val POSITION_SETUP_NAME = 1
        const val POSITION_SETUP_PIN = 2
        const val POSITION_SETUP_COMPLETE = 3
    }

    enum class Button {
        NEXT, BACK
    }

    private var toolbar: Toolbar? = null
    private var viewPager: ViewPager2? = null
    private var viewPagerAdapter: SetupViewPagerAdapter? = null

    private var bottomNavigationBar: LinearLayout? = null
    private var buttonBack: MaterialButton? = null
    private var buttonNext: MaterialButton? = null

    private var runnableButtonBack: Runnable? = null
    private var runnableButtonNext: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        initialize()
    }

    private fun initialize() {
        toolbar = findViewById(R.id.activitySetupToolBar)
        viewPager = findViewById(R.id.activitySetupViewPager)
        bottomNavigationBar = findViewById(R.id.activitySetupBottomNavigationBar)
        buttonBack = findViewById(R.id.activitySetupButtonBack)
        buttonNext = findViewById(R.id.activitySetupButtonNext)

        viewPagerAdapter = SetupViewPagerAdapter(this)

        viewPager?.adapter = viewPagerAdapter
        viewPager?.isUserInputEnabled = false

        setSupportActionBar(toolbar)

        buttonBack?.setOnClickListener {
            viewPager?.currentItem = (viewPager?.currentItem ?: 1) - 1
            runnableButtonBack?.run()

            runnableButtonBack = null
            runnableButtonNext = null
        }

        buttonNext?.setOnClickListener {
            viewPager?.currentItem = (viewPager?.currentItem ?: 0) + 1
            runnableButtonNext?.run()

            runnableButtonBack = null
            runnableButtonNext = null
        }
    }

    override fun onBackPressed() {
        if ((viewPager?.currentItem ?: 0) != 0) {
            viewPager?.currentItem = (viewPager?.currentItem ?: 1) - 1
        } else {
            super.onBackPressed()
        }
    }

    fun toggleButton(button: Button, isEnabled: Boolean) {
        (when (button) {
            Button.BACK -> buttonBack
            Button.NEXT -> buttonNext
        })?.isEnabled = isEnabled
    }

    fun toggleBottomNavigationBar(isVisible: Boolean = true) {
        bottomNavigationBar?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun start() {
        viewPager?.currentItem = POSITION_SETUP_NAME
    }

    fun setPage(page: Int) {
        viewPager?.currentItem = page
    }

    fun setRunnableButtonBack(runnable: Runnable) {
        runnableButtonBack = runnable
    }

    fun setRunnableButtonNext(runnable: Runnable) {
        runnableButtonNext = runnable
    }

}