package com.eduman.core.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.eduman.core.Constants.Companion.KEY_SP_PIN
import com.eduman.core.Constants.Companion.KEY_SP_USERNAME
import com.eduman.core.Constants.Companion.KEY_SP_USE_PIN
import com.eduman.core.Constants.Companion.PREFERENCE_KEY
import java.util.*

class SharedPreferencesUtil(activity: Activity?) {

    // <editor-fold desc="Private variables" defaultstate="collapsed">

    private var preferences: SharedPreferences? = null

    // </editor-fold>

    // <editor-fold desc="Initialization" defaultstate="collapsed">

    init {
        preferences = activity?.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
    }

    // </editor-fold>

    // <editor-fold desc="Get/Set methods" defaultstate="collapsed">

    fun getUsername() = getString(KEY_SP_USERNAME)
    fun setUsername(username: String) = setString(KEY_SP_USERNAME, username)

    fun getPin(): String {
        val encrypted = getString(KEY_SP_PIN)
        return String(Base64.getDecoder().decode(encrypted))
    }
    fun setPin(pin: String) {
        val encrypted = Base64.getEncoder().encodeToString(pin.toByteArray())
        setString(KEY_SP_PIN, encrypted)
    }

    fun getUsePin() = getBoolean(KEY_SP_USE_PIN)
    fun setUsePin(usePin: Boolean) = setBoolean(KEY_SP_USE_PIN, usePin)

    // </editor-fold>

    // <editor-fold desc="Private helper methods" defaultstate="collapsed">

    fun getString(key: String): String? {
        return preferences?.getString(key, null)
    }

    private fun setString(key: String, value: String) {
        preferences?.edit()?.putString(key, value)?.apply()
    }

    private fun getBoolean(key: String, defaultValue: Boolean = false): Boolean? {
        return preferences?.getBoolean(key, defaultValue)
    }

    private fun setBoolean(key: String, value: Boolean) {
        preferences?.edit()?.putBoolean(key, value)?.apply()
    }

    // </editor-fold>

}