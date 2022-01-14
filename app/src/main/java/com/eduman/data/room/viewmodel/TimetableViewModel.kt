package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.entity.Timetable
import com.eduman.data.room.repository.TimetableRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimetableViewModel @Inject constructor(
    private val repository: TimetableRepository
) : ViewModel() {

    fun getAll() = repository.getAll()

    fun insert(timetable: Timetable) = viewModelScope.launch {
        repository.insert(timetable)
    }

    fun delete(timetable: Timetable) = viewModelScope.launch {
        repository.delete(timetable)
    }

}