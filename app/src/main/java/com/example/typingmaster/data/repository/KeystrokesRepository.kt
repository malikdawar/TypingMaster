package com.example.typingmaster.data.repository

import com.example.typingmaster.data.db.KeystrokesDao
import com.example.typingmaster.data.db.KeystrokesData
import javax.inject.Inject

/**
 * KeystrokesRepository.kt
 * @author Malik Dawar
 */
class KeystrokesRepository
    @Inject
    constructor(private val keystrokesDao: KeystrokesDao) {
        suspend fun insertKeystrokesData(keystrokesData: KeystrokesData) {
            keystrokesDao.insertKeystrokesData(keystrokesData)
        }

        suspend fun getAllKeystrokes(): List<KeystrokesData> {
            return keystrokesDao.getAllKeystrokes()
        }
    }
