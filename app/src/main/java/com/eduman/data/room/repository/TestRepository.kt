package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.TestDAO
import com.eduman.data.room.entity.Test
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val dao: TestDAO
) {

    fun getAll(subjectId: Int) = dao.getAll(subjectId).asLiveData()

    fun getAllWithGrade(subjectId: Int) = dao.getAllWithGrade(subjectId).asLiveData()

    fun getNext(subjectId: Int, amount: Int) = dao.getNext(subjectId, amount).asLiveData()

    fun getNext(amount: Int) = dao.getNext(amount).asLiveData()

    fun getGrade(testId: Int) = dao.getGrade(testId).asLiveData()

    suspend fun insert(test: Test) = dao.insert(test)

    suspend fun delete(test: Test) = dao.delete(test)

    suspend fun deleteBySubjectId(subjectId: Int) = dao.deleteBySubjectId(subjectId)

}