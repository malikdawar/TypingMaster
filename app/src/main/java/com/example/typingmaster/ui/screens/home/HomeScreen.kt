package com.example.typingmaster.ui.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.typingmaster.data.db.KeystrokesData
import com.example.typingmaster.ui.screens.home.model.HomeViewModel

/**
 * HomeTypingScreen.kt
 * @author Malik Dawar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTypingScreen(
    userName: String,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        // Initial call to set up paragraph and text coloring
        viewModel.onUserInputChanged(TextFieldValue())
    }

    val orientation = LocalConfiguration.current.orientation
    val userInput by viewModel.userInput.collectAsState()
    val wpm by viewModel.wpm.collectAsState()
    val coloredText by viewModel.coloredText.collectAsState()
    val onValueChange: (TextFieldValue) -> Unit = { textFieldValue ->
        val words = textFieldValue.text.split(" ")
        if (words.size <= viewModel.getParagraphWordCount()) {
            viewModel.onUserInputChanged(textFieldValue)
        }
    }

    val annotatedString =
        buildAnnotatedString {
            coloredText.forEach { coloredSegment ->
                withStyle(
                    style = SpanStyle(color = Color(coloredSegment.color)),
                ) {
                    append("${coloredSegment.text} ")
                }
            }
        }

    Column(
        modifier =
            Modifier
                .wrapContentHeight()
                .padding(16.dp),
    ) {
        Text(
            text = "Welcome $userName",
            modifier = Modifier.padding(bottom = 16.dp),
            style =
                TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 28.sp,
                ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = annotatedString,
            modifier = Modifier.padding(bottom = 16.dp),
            style =
                TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 18.sp,
                ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "WPM: $wpm",
            modifier = Modifier.padding(bottom = 16.dp),
            style =
                TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 18.sp,
                ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userInput,
            onValueChange = onValueChange,
            modifier =
                Modifier
                    .testTag("InputField")
                    .fillMaxWidth()
                    .height(256.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    .onKeyEvent { keyEvent ->
                        viewModel.sendAnalyticsEvent(
                            with(keyEvent.nativeKeyEvent) {
                                KeystrokesData(
                                    username = userName,
                                    keyPressTime = downTime,
                                    keyReleaseTime = eventTime,
                                    keyCode = scanCode,
                                    phoneOrientation = orientation,
                                )
                            },
                        )
                        true
                    },
            colors =
                TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            keyboardOptions =
                KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None,
                ),
        )
    }
}
