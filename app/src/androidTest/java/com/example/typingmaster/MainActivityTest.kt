package com.example.typingmaster

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.typingmaster.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * MainActivityTest- UI tests for the MainActivity
 * @author Malik Dawar
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigationToHomeTypingScreenTest() {
        composeTestRule.onNodeWithTag("fieldUserName")
            .performTextInput("John Doe")
        // Click the button on the login screen
        composeTestRule.onNodeWithTag("ButtonContinue").performClick()

        // Check if a Text with certain content is displayed on the home screen
        composeTestRule
            .onNodeWithText("Welcome John Doe", useUnmergedTree = true)
            .assertIsDisplayed()
    }
}
