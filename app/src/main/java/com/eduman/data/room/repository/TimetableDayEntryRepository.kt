package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.TimetableDayEntryDAO
import com.eduman.data.room.entity.TimetableDayEntry
import javax.inject.Inject

class TimetableDayEntryRepository @Inject constructor(
    private val dao: TimetableDayEntryDAO
) {

    fun getAll(timetableDayId: Int) = dao.getAll(timetableDayId).asLiveData()

    suspend fun insert(timetableDayEntry: TimetableDayEntry) = dao.insert(timetableDayEntry)

    suspend fun delete(timetableDayEntry: TimetableDayEntry) = dao.delete(timetableDayEntry)

}