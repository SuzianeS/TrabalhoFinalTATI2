package com.example.quiz.network.models.game

import com.example.quiz.network.models.category.CategoryFields

data class Game(
    var difficulty: String?,
    var category: CategoryFields?,
    var asserts: Int = 0,
    var errors: Int = 0,
    var score: Int = 0,
    var creation: String? = null,
    var status: String? = null,
    var startedAt: String? = null
) {

}