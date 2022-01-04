package com.eduman.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.eduman.data.room.entitiy.relation.GradeAndTestCount
import kotlinx.coroutines.flow.Flow

@Dao
interface CoreDAO {

    @Query("SELECT ( SELECT COUNT(*) FROM grades WHERE subject_id = :subjectId) AS grade_count, (SELECT COUNT(*) FROM tests WHERE subject_id = :subjectId) AS test_count")
    fun checkGradeAndTestCount(subjectId: Int): Flow<GradeAndTestCount>

}