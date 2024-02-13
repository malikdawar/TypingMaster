package com.example.typingmaster.data.usecases

import com.example.typingmaster.data.db.KeystrokesData
import com.example.typingmaster.data.repository.KeystrokesRepository
import javax.inject.Inject

/**
 * KeystrokesUseCase.kt
 * @author Malik Dawar
 */

class KeystrokesUseCase
    @Inject
    constructor(private val keystrokesRepository: KeystrokesRepository) {
        suspend fun invoke(keystrokesData: KeystrokesData) {
            keystrokesRepository.insertKeystrokesData(keystrokesData)
        }
    }
