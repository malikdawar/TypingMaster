package com.example.typingmaster

import androidx.compose.ui.text.input.TextFieldValue
import com.example.typingmaster.data.db.KeystrokesData
import com.example.typingmaster.data.usecases.KeystrokesUseCase
import com.example.typingmaster.ui.screens.home.model.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * HomeViewModelTest- Unit tests for the HomeViewModel
 * @author Malik Dawar
 */
class HomeViewModelTest {
    @Mock
    lateinit var keystrokesUseCase: KeystrokesUseCase

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher) // Set main dispatcher for tests
        viewModel = HomeViewModel(keystrokesUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain() // Reset main dispatcher after the test
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `onUserInputChanged updates user input`() {
        val input = "Example input"

        viewModel.onUserInputChanged(TextFieldValue(input))

        assertEquals(input, viewModel.userInput.value.text)
    }

    @Test
    fun `updateWPM calculates correct WPM doesn't output negative with correct words`() {
        // Setup
        val input = "He thought he would light the fire when"
        val textFieldValue = MutableStateFlow(TextFieldValue(input))
        viewModel.onUserInputChanged(textFieldValue.value)

        // Action
        viewModel.updateWPM()

        // Assert
        assertNotEquals(-1, viewModel.wpm.value)
    }

    @Test
    fun `updateWPM calculates zero WPM with Wrong input`() {
        // Setup
        val input = "yh uj ihu8yhu hu8 yuy 87y 8y8 8 y8 y8 y8y 8y hku y8y 8iy 88 yy huy 8y ih"
        val textFieldValue = MutableStateFlow(TextFieldValue(input))
        viewModel.onUserInputChanged(textFieldValue.value)

        // Action
        viewModel.updateWPM()

        // Assert
        assertEquals(0, viewModel.wpm.value)
    }

    @Test
    fun `sendAnalyticsEvent invokes keystrokesUseCase use case`() {
        // Setup
        val keystrokesData =
            KeystrokesData(
                username = "",
                keyPressTime = 0,
                keyReleaseTime = 1,
                keyCode = 1,
                phoneOrientation = 1,
            )

        // Action
        viewModel.sendAnalyticsEvent(keystrokesData)
        runBlocking {
            // Verify
            verify(keystrokesUseCase).invoke(keystrokesData)
        }
    }

    @Test
    fun `updateWPM calculates correct word count`() {
        // Setup
        val input = "He thought he would light the fire"
        val textFieldValue = MutableStateFlow(TextFieldValue(input))

        // Action
        viewModel.onUserInputChanged(textFieldValue.value)
        viewModel.updateWPM()

        // Assert
        assertEquals(7, viewModel.correctWordCount)
    }

    @Test
    fun `updateWPM calculates zero WPM with no user input`() {
        // Setup
        val textFieldValue = MutableStateFlow(TextFieldValue(""))

        // Action
        viewModel.onUserInputChanged(textFieldValue.value)
        viewModel.updateWPM()

        // Assert
        assertEquals(0, viewModel.wpm.value)
    }
}
