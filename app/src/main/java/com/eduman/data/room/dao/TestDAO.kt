package com.eduman.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eduman.data.room.entitiy.Test
import kotlinx.coroutines.flow.Flow

@Dao
interface TestDAO {

    @Query("SELECT * FROM tests WHERE subject_id = :subjectId")
    fun getAll(subjectId: Int): Flow<List<Test>>

    @Query("SELECT * FROM tests WHERE subject_id = :subjectId ORDER BY date ASC LIMIT :amount")
    fun getNext(subjectId: Int, amount: Int): Flow<List<Test>>

    @Insert
    suspend fun insert(tes: Test)

    @Delete
    suspend fun delete(tes: Test)

}