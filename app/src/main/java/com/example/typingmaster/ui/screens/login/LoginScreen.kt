package com.example.typingmaster.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.typingmaster.ui.screens.login.model.LoginViewModel

/**
 * UserLoginScreen.kt
 * @author Malik Dawar
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UserLoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onUsernameEntered: (String) -> Unit,
) {
    var username by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Enter Username") },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag("fieldUserName"),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onUsernameEntered(username)
            },
            enabled = username.isNotBlank(),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag("ButtonContinue"),
        ) {
            val buttonText =
                if (username.isNotBlank()) {
                    "Continue"
                } else {
                    "Start Typing"
                }
            Text(buttonText)
        }
    }
}
