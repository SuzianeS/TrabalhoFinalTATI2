package com.example.quiz.network.models.game

data class OutputFinishGame(
    var status: String,
    var message: String,
    var data: FinishGame?
)
data class FinishGame(
    val game: FinishGameFields
)

data class FinishGameFields(
    val status: String,
    val started_at: String,
    val finished_at: String,
    val score: Number
)

