package com.example.typingmaster

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.typingmaster.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UserLoginScreenTest- UI tests for the UserLoginScreen
 * @author Malik Dawar
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UserLoginScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testUserLoginScreen() {
        // Type username into the text field
        composeTestRule.onNodeWithTag("fieldUserName")
            .performTextInput("John Doe")

        // Click the Continue button
        composeTestRule.onNodeWithText("Continue")
            .performClick()

        composeTestRule.onNodeWithText("Welcome John Doe")
            .assertExists()
    }

    @Test
    fun testButtonIsDisabledWhenFieldEmpty() {
        // Clear username field initially
        composeTestRule.onNodeWithTag("fieldUserName")
            .performTextInput("") // Clearing the field

        // the button should be disabled
        composeTestRule.onNodeWithTag("ButtonContinue")
            .assertIsNotEnabled()
    }

    @Test
    fun testButtonIsDisabledWhenFieldNotEmpty() {
        // Type username into the text field
        composeTestRule.onNodeWithTag("fieldUserName")
            .performTextInput("John Doe")

        // After typing, the button should be enabled
        composeTestRule.onNodeWithTag("ButtonContinue")
            .assertIsEnabled()
    }

    @Test
    fun testButtonIsTextWhenFieldEmpty() {
        // Clear username field initially
        composeTestRule.onNodeWithTag("fieldUserName")
            .performTextInput("")

        // the button should show Start Typing
        composeTestRule.onNodeWithTag("ButtonContinue")
            .assertTextEquals("Start Typing")
    }

    @Test
    fun testButtonIsTextWhenFieldNotEmpty() {
        // Type username into the text field
        composeTestRule.onNodeWithTag("fieldUserName")
            .performTextInput("John Doe")

        // the button should show Continue
        composeTestRule.onNodeWithTag("ButtonContinue")
            .assertTextEquals("Continue")
    }
}
