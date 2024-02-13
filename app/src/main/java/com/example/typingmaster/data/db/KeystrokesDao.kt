package com.example.typingmaster.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * KeystrokesDao.kt
 * @author Malik Dawar
 */

@Dao
interface KeystrokesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeystrokesData(keystrokesData: KeystrokesData)

    @Query("SELECT * FROM keystrokes_data")
    suspend fun getAllKeystrokes(): List<KeystrokesData>
}
