package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.entity.TimetableDay
import com.eduman.data.room.repository.TimetableDayRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimetableDayViewModel @Inject constructor(
    private val repository: TimetableDayRepository
) : ViewModel() {

    fun getAll(timetableId: Int) = repository.getAll(timetableId)

    fun insert(timetableDay: TimetableDay) = viewModelScope.launch {
        repository.insert(timetableDay)
    }

    fun delete(timetableDay: TimetableDay) = viewModelScope.launch {
        repository.delete(timetableDay)
    }

}