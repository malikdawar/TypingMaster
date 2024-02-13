package com.example.typingmaster.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * KeystrokesDao.kt
 * @author Malik Dawar
 */

@Database(entities = [KeystrokesData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun keystrokesDao(): KeystrokesDao
}
