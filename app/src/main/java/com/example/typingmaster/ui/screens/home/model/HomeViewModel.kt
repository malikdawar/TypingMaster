package com.example.typingmaster.ui.screens.home.model

import android.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.typingmaster.data.db.KeystrokesData
import com.example.typingmaster.data.usecases.KeystrokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

/**
 * HomeViewModel.kt
 * @author Malik Dawar
 */

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val keystrokesUseCase: KeystrokesUseCase,
    ) : ViewModel() {
        private val _userInput = MutableStateFlow(TextFieldValue())
        val userInput: StateFlow<TextFieldValue> get() = _userInput

        private val _coloredText = MutableStateFlow<List<ColoredText>>(emptyList())
        val coloredText: StateFlow<List<ColoredText>> get() = _coloredText

        private val _wpm = MutableStateFlow(0)
        val wpm: StateFlow<Int> = _wpm

        @VisibleForTesting
        var correctWordCount = 0
        private var startTime = System.currentTimeMillis()

        private val paragraph =
            "He thought he would light the fire when" +
                "he got inside, and make himself some" +
                "breakfast, just to pass away the time;" +
                "but he did not seem able to handle anything" +
                "from a scuttleful of coals to a "

        private val paragraphWords = paragraph.split(" ")

        fun getParagraphWordCount(): Int {
            return paragraphWords.size
        }

        fun onUserInputChanged(input: TextFieldValue) {
            val currentValue = _userInput.value
            if (input.text.length >= currentValue.text.length) {
                _userInput.value = input
                updateColoredText()
                updateWPM()
            }
        }

        private fun updateColoredText() {
            val coloredTextList = mutableListOf<ColoredText>()
            paragraphWords.forEachIndexed { index, word ->
                val color =
                    if (_userInput.value.text.split(" ").getOrNull(index) == word) {
                        Color.GREEN // If the word matches, set color to green
                    } else {
                        Color.RED // If the word doesn't match, set color to red
                    }

                coloredTextList.add(ColoredText(word, color))
            }
            _coloredText.value = coloredTextList
        }

        @VisibleForTesting
        fun updateWPM() {
            val userInputWords = _userInput.value.text.split(" ")
            correctWordCount = userInputWords.count { it in paragraphWords }

            val elapsedTime = System.currentTimeMillis() - startTime
            _wpm.value =
                if (_userInput.value.text.isBlank()) {
                    // Reset to 0 when there's no user input
                    0
                } else if (elapsedTime > 0) {
                    // Calculate WPM based on correct word count and time
                    (correctWordCount * 60000 / elapsedTime).toInt()
                } else {
                    0
                }
        }

        fun sendAnalyticsEvent(keystrokesData: KeystrokesData) {
            viewModelScope.launch {
                keystrokesUseCase.invoke(
                    keystrokesData,
                )
            }
        }
    }
