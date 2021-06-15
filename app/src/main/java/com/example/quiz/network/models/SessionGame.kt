package com.example.quiz.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game")
data class SessionGame (
    var difficulty: String?,
    var category: String?,
    var asserts: Int = 0,
    var errors: Int = 0,
    var score: Int = 0,
    var categoria: String,
    var num: Int,
    var logado: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
