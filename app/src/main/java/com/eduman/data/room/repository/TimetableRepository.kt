package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.TimetableDAO
import com.eduman.data.room.entity.Timetable
import javax.inject.Inject

class TimetableRepository @Inject constructor(
    private val dao: TimetableDAO
) {

    fun getAll() = dao.getAll().asLiveData()

    suspend fun insert(timetable: Timetable) = dao.insert(timetable)

    suspend fun delete(timetable: Timetable) = dao.delete(timetable)

}