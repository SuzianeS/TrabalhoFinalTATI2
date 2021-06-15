package com.example.quiz.network.models.game

data class OutputFindOrCreateGame(
    var status: String,
    var message: String,
    var data: FindOrCreate?
)

data class FindOrCreate(
    val game: FindOrCreateFields
)

data class FindOrCreateFields(
    val creation: String,
    val status: String,
    val started_at: String,
    val score: Number
)

