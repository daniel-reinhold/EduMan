package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.SettingDAO
import com.eduman.data.util.Pin
import javax.inject.Inject

class SettingRepository @Inject constructor(
    private val dao: SettingDAO
) {

    suspend fun saveUsername(username: String) = dao.saveUsername(username)

    fun getUsername() = dao.getUsername().asLiveData()

    suspend fun savePin(pin: Pin) = dao.savePin(pin)

    fun getPin() = dao.getPin().asLiveData()

    fun getAllSettings() = dao.getAllSettings().asLiveData()

}