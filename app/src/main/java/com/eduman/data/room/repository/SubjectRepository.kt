package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.SubjectDAO
import com.eduman.data.room.entitiy.Subject
import javax.inject.Inject

class SubjectRepository @Inject constructor(
    val dao: SubjectDAO
) {

    fun getAll() = dao.getAll().asLiveData()

    fun searchByTitle(title: String) = dao.searchByTitle(title).asLiveData()

    suspend fun insert(subject: Subject) = dao.insert(subject)

    suspend fun delete(subject: Subject) = dao.delete(subject)

}