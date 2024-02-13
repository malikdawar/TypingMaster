package com.example.typingmaster

import com.example.typingmaster.data.db.KeystrokesData
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * KeystrokesDataTest- Unit tests for the KeystrokesData
 * @author Malik Dawar
 */
class KeystrokesDataTest {
    @Test
    fun `test KeystrokesData entity properties`() {
        // Given
        val id = 1L
        val username = "TestUser"
        val keyPressTime = 123L
        val keyReleaseTime = 456L
        val keyCode = 789
        val phoneOrientation = 90

        // When
        val keystrokesData =
            KeystrokesData(
                id = id,
                username = username,
                keyPressTime = keyPressTime,
                keyReleaseTime = keyReleaseTime,
                keyCode = keyCode,
                phoneOrientation = phoneOrientation,
            )

        // Then
        assertEquals(keystrokesData.id, id)
        assertEquals(keystrokesData.username, username)
        assertEquals(keystrokesData.keyPressTime, keyPressTime)
        assertEquals(keystrokesData.keyReleaseTime, keyReleaseTime)
        assertEquals(keystrokesData.keyCode, keyCode)
        assertEquals(keystrokesData.phoneOrientation, phoneOrientation)
    }
}
