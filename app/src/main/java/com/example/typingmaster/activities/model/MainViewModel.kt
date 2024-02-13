package com.example.typingmaster.activities.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * MainViewModel.kt
 * @author Malik Dawar
 */

class MainViewModel : ViewModel() {
    private val _username = mutableStateOf("")
    val username: State<String> = _username

    fun setUserName(userName: String) {
        _username.value = userName
    }
}
