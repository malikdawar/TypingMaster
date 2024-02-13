package com.example.typingmaster.ui.screens.login.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.typingmaster.data.repository.KeystrokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// please ignore this VM, crated it to see the saved logs for the testing purpose,
// avoided UserCase layer to save time since it wasn't the part of assignment

/**
 * LoginViewModel.kt
 * @author Malik Dawar
 */
@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val keystrokesRepository: KeystrokesRepository,
    ) : ViewModel() {
        fun getKeyStrokeDbData() {
            viewModelScope.launch {
                val allEntries =
                    withContext(Dispatchers.IO) {
                        keystrokesRepository.getAllKeystrokes()
                    }
                allEntries.forEach { entry ->
                    Log.d("KeystrokesEntry", "KeystrokesEntry: $entry")
                }
            }
        }
    }
