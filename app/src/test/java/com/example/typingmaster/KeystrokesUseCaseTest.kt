package com.example.typingmaster

import com.example.typingmaster.data.db.KeystrokesData
import com.example.typingmaster.data.repository.KeystrokesRepository
import com.example.typingmaster.data.usecases.KeystrokesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * KeystrokesUseCaseTest- Unit tests for the KeystrokesUseCase
 * @author Malik Dawar
 */
@RunWith(MockitoJUnitRunner::class)
class KeystrokesUseCaseTest {
    @Mock
    lateinit var keystrokesRepository: KeystrokesRepository

    lateinit var keystrokesUseCase: KeystrokesUseCase

    @Before
    fun setUp() {
        keystrokesUseCase = KeystrokesUseCase(keystrokesRepository)
    }

    @Test
    fun `verify KeystrokesUseCase invokes addKeystrokes method in repository`() {
        runBlocking {
            // Given
            val keystrokesData =
                KeystrokesData(
                    username = "user1",
                    keyPressTime = 123,
                    keyReleaseTime = 456,
                    keyCode = 789,
                    phoneOrientation = 2,
                )

            // When
            keystrokesUseCase.invoke(keystrokesData)

            // Then
            verify(keystrokesRepository).insertKeystrokesData(keystrokesData)
        }
    }
}
