package com.example.typingmaster.di

import android.content.Context
import androidx.room.Room
import com.example.typingmaster.data.db.AppDatabase
import com.example.typingmaster.data.db.KeystrokesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DatabaseModule.kt
 * @author Malik Dawar
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "keystrokes_database",
        ).build()
    }

    @Provides
    @Singleton
    fun provideKeystrokesDao(database: AppDatabase): KeystrokesDao {
        return database.keystrokesDao()
    }
}
