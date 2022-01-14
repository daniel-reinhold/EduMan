package com.eduman.data.room.dao

import androidx.room.*
import com.eduman.data.room.entity.TimetableDay
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetableDayDAO {

    @Query("SELECT * FROM timetable_days WHERE timetable_id = :timetableId")
    fun getAll(timetableId: Int): Flow<List<TimetableDay>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timetableDay: TimetableDay)

    @Delete
    suspend fun delete(timetableDay: TimetableDay)

}