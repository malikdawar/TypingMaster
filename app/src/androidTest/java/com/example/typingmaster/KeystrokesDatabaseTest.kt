package com.example.typingmaster

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.typingmaster.data.db.AppDatabase
import com.example.typingmaster.data.db.KeystrokesDao
import com.example.typingmaster.data.db.KeystrokesData
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * KeystrokesDatabaseTest- UI tests for the KeystrokesDatabase
 * @author Malik Dawar
 */
@RunWith(AndroidJUnit4::class)
class KeystrokesDatabaseTest {
    private lateinit var keystrokesDao: KeystrokesDao
    private lateinit var database: AppDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        keystrokesDao = database.keystrokesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAndGetKeystrokes() =
        runBlocking {
            val keystrokesData =
                KeystrokesData(
                    username = "user1",
                    keyPressTime = 123,
                    keyReleaseTime = 456,
                    keyCode = 789,
                    phoneOrientation = 2,
                )

            // Insert
            keystrokesDao.insertKeystrokesData(keystrokesData)

            // Get by username
            val retrievedData = keystrokesDao.getAllKeystrokes()

            assertEquals(1, retrievedData.size)
            assertEquals(keystrokesData.username, retrievedData[0].username)
            assertEquals(keystrokesData.keyPressTime, retrievedData[0].keyPressTime)
            assertEquals(keystrokesData.keyReleaseTime, retrievedData[0].keyReleaseTime)
            assertEquals(keystrokesData.keyCode, retrievedData[0].keyCode)
            assertEquals(keystrokesData.phoneOrientation, retrievedData[0].phoneOrientation)
        }
}
