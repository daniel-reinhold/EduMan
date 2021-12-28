package com.eduman.data.room.dao

import androidx.room.*
import com.eduman.data.room.entitiy.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDAO {

    @Query("SELECT * FROM subjects")
    fun getAll(): Flow<List<Subject>>

    @Query("SELECT * FROM subjects WHERE title = :title")
    fun searchByTitle(title: String): Flow<List<Subject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Subject)

    @Delete
    suspend fun delete(subject: Subject)

}