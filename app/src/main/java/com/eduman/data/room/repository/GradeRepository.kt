package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.GradeDAO
import com.eduman.data.room.entitiy.Grade
import javax.inject.Inject

class GradeRepository @Inject constructor(
    private val dao: GradeDAO
) {

    fun getAll(subjectId: Int) = dao.getAll(subjectId).asLiveData()

    fun getLast(subjectId: Int, amount: Int) = dao.getLast(subjectId, amount).asLiveData()

    suspend fun insert(grade: Grade) = dao.insert(grade)

    suspend fun delete(grade: Grade) = dao.delete(grade)

}