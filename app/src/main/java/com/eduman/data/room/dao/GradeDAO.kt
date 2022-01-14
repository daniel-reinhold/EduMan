package com.eduman.data.room.dao

import androidx.room.*
import com.eduman.data.room.entity.Grade
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDAO {

    @Query("SELECT * FROM grades WHERE subject_id = :subjectId")
    fun getAll(subjectId: Int): Flow<List<Grade>>

    @Query("SELECT * FROM grades WHERE subject_id = :subjectId ORDER BY created_at DESC LIMIT :amount")
    fun getLast(subjectId: Int, amount: Int): Flow<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)

    @Query("DELETE FROM grades WHERE subject_id = :subjectId")
    suspend fun deleteBySubjectId(subjectId: Int)

}