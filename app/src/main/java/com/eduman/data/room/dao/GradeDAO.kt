package com.eduman.data.room.dao

import androidx.room.*
import com.eduman.data.room.entitiy.Grade
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDAO {

    @Query("SELECT * FROM grades WHERE subject_id = :subjectId")
    fun getAll(subjectId: Int): Flow<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)

}