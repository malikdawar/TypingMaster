package com.example.typingmaster

import com.example.typingmaster.data.db.KeystrokesDao
import com.example.typingmaster.data.db.KeystrokesData
import com.example.typingmaster.data.repository.KeystrokesRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * KeystrokesRepositoryTest- Unit tests for the KeystrokesRepository
 * @author Malik Dawar
 */
@RunWith(MockitoJUnitRunner::class)
class KeystrokesRepositoryTest {
    @Mock
    private lateinit var keystrokesDao: KeystrokesDao

    private lateinit var keystrokesRepository: KeystrokesRepository

    @Before
    fun setup() {
        keystrokesRepository = KeystrokesRepository(keystrokesDao)
    }

    @Test
    fun `test addKeystrokes`() =
        runBlocking {
            // Given
            val keystrokesData =
                KeystrokesData(
                    username = "Jeo don",
                    keyPressTime = 0,
                    keyReleaseTime = 1,
                    keyCode = 1,
                    phoneOrientation = 1,
                )

            // When
            keystrokesRepository.insertKeystrokesData(keystrokesData)

            // Then
            verify(keystrokesDao).insertKeystrokesData(keystrokesData)
        }

    @Test
    fun `test getAllKeystrokes`() {
        runBlocking {
            // Given
            val testDataList =
                listOf(
                    KeystrokesData(
                        username = "Jeo don1",
                        keyPressTime = 0,
                        keyReleaseTime = 1,
                        keyCode = 1,
                        phoneOrientation = 2,
                    ),
                    KeystrokesData(
                        username = "Jeo don2",
                        keyPressTime = 10,
                        keyReleaseTime = 10,
                        keyCode = 11,
                        phoneOrientation = 12,
                    ),
                )

            `when`(keystrokesDao.getAllKeystrokes()).thenReturn(testDataList)

            // When
            val result = keystrokesRepository.getAllKeystrokes()

            // Then
            assertEquals(testDataList, result)
        }
    }
}
