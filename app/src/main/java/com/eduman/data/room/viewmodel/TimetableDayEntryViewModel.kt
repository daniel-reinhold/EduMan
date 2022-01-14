package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.entity.TimetableDayEntry
import com.eduman.data.room.repository.TimetableDayEntryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimetableDayEntryViewModel @Inject constructor(
    private val repository: TimetableDayEntryRepository
) : ViewModel() {

    fun getAll(timetableDayId: Int) = repository.getAll(timetableDayId)

    fun insert(timetableDayEntry: TimetableDayEntry) = viewModelScope.launch {
        repository.insert(timetableDayEntry)
    }

    fun delete(timetableDayEntry: TimetableDayEntry) = viewModelScope.launch {
        repository.delete(timetableDayEntry)
    }

}