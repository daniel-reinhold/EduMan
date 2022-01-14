package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.TimetableDayDAO
import com.eduman.data.room.entity.TimetableDay
import javax.inject.Inject

class TimetableDayRepository @Inject constructor(
    private val dao: TimetableDayDAO
) {

    fun getAll(timetableId: Int) = dao.getAll(timetableId).asLiveData()

    suspend fun insert(timetableDay: TimetableDay) = dao.insert(timetableDay)

    suspend fun delete(timetableDay: TimetableDay) = dao.delete(timetableDay)

}