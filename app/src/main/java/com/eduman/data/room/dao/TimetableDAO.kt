package com.eduman.data.room.dao

import androidx.room.*
import com.eduman.data.room.entity.Timetable
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetableDAO {

    @Query("SELECT * from timetables")
    fun getAll(): Flow<List<Timetable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timetable: Timetable)

    @Delete
    suspend fun delete(timetable: Timetable)

}