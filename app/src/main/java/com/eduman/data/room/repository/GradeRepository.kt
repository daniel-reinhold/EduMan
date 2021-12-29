package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.GradeDAO
import com.eduman.data.room.entitiy.Grade
import javax.inject.Inject

class GradeRepository @Inject constructor(
    val dao: GradeDAO
) {

    fun getAll(subjectId: Int) = dao.getAll(subjectId).asLiveData()

    suspend fun insert(grade: Grade) = dao.insert(grade)

    suspend fun delete(grade: Grade) = dao.delete(grade)

}