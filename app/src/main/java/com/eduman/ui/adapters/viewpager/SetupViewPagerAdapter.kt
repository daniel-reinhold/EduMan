package com.eduman.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eduman.ui.activities.SetupActivity
import com.eduman.ui.fragments.setup.SetupCompleteFragment
import com.eduman.ui.fragments.setup.SetupNameFragment
import com.eduman.ui.fragments.setup.SetupPinFragment
import com.eduman.ui.fragments.setup.SetupStartFragment

class
SetupViewPagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = SetupActivity.STEPS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            SetupActivity.POSITION_SETUP_START -> SetupStartFragment()
            SetupActivity.POSITION_SETUP_NAME -> SetupNameFragment()
            SetupActivity.POSITION_SETUP_PIN -> SetupPinFragment()
            SetupActivity.POSITION_SETUP_COMPLETE -> SetupCompleteFragment()
            else -> SetupNameFragment()
        }
    }

}