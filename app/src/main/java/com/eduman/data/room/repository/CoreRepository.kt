package com.eduman.data.room.repository

import androidx.lifecycle.asLiveData
import com.eduman.data.room.dao.CoreDAO
import javax.inject.Inject

class CoreRepository @Inject constructor(
    val dao: CoreDAO
) {

    fun checkGradeAndTestCount(subjectId: Int) = dao.checkGradeAndTestCount(subjectId).asLiveData()

}