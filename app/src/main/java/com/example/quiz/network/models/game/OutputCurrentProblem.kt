package com.example.quiz.network.models.game

data class OutputCurrentProblem(
    var status: String,
    var message: String,
    var data: DataProblem?
)
