package com.eduman.ui.fragments.setup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetupViewModel : ViewModel() {

    val name = MutableLiveData("")
    val pin = MutableLiveData("")
    val usePin = MutableLiveData(false)

    fun setName(name: String) {
        this.name.value = name
    }

    fun setPIN(pin: String) {
        this.pin.value = pin
    }

    fun setUsePin(usePin: Boolean) {
        this.usePin.value = usePin
    }

}