package com.eduman.data.room.dao

import androidx.room.*
import com.eduman.data.room.entity.Test
import com.eduman.data.room.entity.relation.TestAndGrade
import com.eduman.data.room.entity.relation.TestAndSubject
import kotlinx.coroutines.flow.Flow

@Dao
interface TestDAO {

    @Query("SELECT * FROM tests WHERE subject_id = :subjectId")
    fun getAll(subjectId: Int): Flow<List<Test>>

    @Query("SELECT * FROM tests WHERE subject_id = :subjectId ORDER BY date ASC LIMIT :amount")
    fun getNext(subjectId: Int, amount: Int): Flow<List<Test>>

    @Transaction
    @Query("SELECT * FROM tests ORDER BY date ASC LIMIT :amount")
    fun getNext(amount: Int): Flow<List<TestAndSubject>>

    @Transaction
    @Query("SELECT * FROM tests WHERE id = :testId LIMIT 1")
    fun getGrade(testId: Int): Flow<TestAndGrade>

    @Insert
    suspend fun insert(tes: Test)

    @Delete
    suspend fun delete(tes: Test)

}