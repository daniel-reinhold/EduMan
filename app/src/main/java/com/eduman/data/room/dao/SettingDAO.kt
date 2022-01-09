package com.eduman.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.eduman.data.room.entitiy.Setting
import com.eduman.data.util.Pin
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDAO {

    @Query("UPDATE settings SET user_name = :username WHERE id=1")
    suspend fun saveUsername(username: String)

    @Query("SELECT user_name FROM settings WHERE id=1")
    fun getUsername(): Flow<String?>

    @Query("UPDATE settings SET pin = :pin WHERE id=1")
    suspend fun savePin(pin: Pin?)

    @Query("SELECT pin FROM settings WHERE id=1")
    fun getPin(): Flow<Pin>

    @Query("SELECT * FROM settings WHERE id=1")
    fun getAllSettings(): Flow<Setting>

}