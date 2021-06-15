package com.example.quiz.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessionGame")
data class Session (
    val email: String?,
    val name: String?,
    val token: String?,
    val logado: Int?
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

