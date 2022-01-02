package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.CoreDao
import javax.inject.Inject

class CoreRepository @Inject constructor(
    val dao: CoreDao
) {

    fun checkGradeAndTestCount(subjectId: Int) = dao.checkGradeAndTestCount(subjectId).asLiveData()

}