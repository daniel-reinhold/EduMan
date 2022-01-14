package com.eduman.data.room.dao

import androidx.room.*
import com.eduman.data.room.entity.TimetableDayEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetableDayEntryDAO {

    @Query("SELECT * FROM timetable_day_entries WHERE timetable_day_id = :timetableDayId ORDER BY position ASC")
    fun getAll(timetableDayId: Int): Flow<List<TimetableDayEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timetableDayEntry: TimetableDayEntry)

    @Delete
    suspend fun delete(timetableDayEntry: TimetableDayEntry)

}