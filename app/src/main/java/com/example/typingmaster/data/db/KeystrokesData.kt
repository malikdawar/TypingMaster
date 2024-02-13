package com.example.typingmaster.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * KeystrokesData.kt
 * @author Malik Dawar
 */

@Entity(tableName = "keystrokes_data")
data class KeystrokesData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val username: String,
    val keyPressTime: Long,
    val keyReleaseTime: Long,
    val keyCode: Int,
    val phoneOrientation: Int,
)
