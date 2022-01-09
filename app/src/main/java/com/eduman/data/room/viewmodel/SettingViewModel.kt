package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.repository.SettingRepository
import com.eduman.data.util.Pin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: SettingRepository
) : ViewModel() {

    fun saveUsername(username: String) = viewModelScope.launch {
        repository.saveUsername(username)
    }

    fun getUsername() = repository.getUsername()

    fun savePin(pin: Pin) = viewModelScope.launch {
        repository.savePin(pin)
    }

    fun getPin() = repository.getPin()

    fun getAllSettings() = repository.getAllSettings()

}