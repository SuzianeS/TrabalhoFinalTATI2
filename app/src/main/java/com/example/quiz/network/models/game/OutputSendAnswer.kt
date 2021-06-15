package com.example.quiz.network.models.game

import com.example.quiz.network.models.problem.Answer
import com.google.gson.annotations.SerializedName

data class OutputSendAnswer(
    var status: String,
    var message: String,
    var data: DataAnswer
)

data class DataAnswer(
    var answer: AnswerSendFields
)

data class AnswerSendFields(
    var status: String,
    var score: Int,
    @SerializedName("correct_answer")
    var correctAnswer: Answer
) {
    fun isCorrect() : Boolean {
        return status != "incorrect"
    }
}
