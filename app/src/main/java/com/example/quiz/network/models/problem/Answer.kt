package com.example.quiz.network.models.problem

data class Answer(
    var description: String,
    var order: Number
) {
    override fun equals(other: Any?) = other is Answer && this.order == other.order
}