package com.example.quiz.network.models.game

import com.example.quiz.network.models.category.Category
import com.example.quiz.network.models.problem.Answer

data class OutputNextProblem(
    var status: String,
    var message: String,
    var data: DataProblem?
)

data class DataProblem(
    var problem: DataNextProblemFields
)

data class DataNextProblemFields(
    var question: String,
    var difficulty: String,
    var category: Category,
    var answers: List<Answer>
) {
    var id: Long? = null
}