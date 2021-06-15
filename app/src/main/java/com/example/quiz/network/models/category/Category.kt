package com.example.quiz.network.models.category

data class Category(
    var success: String,
    var message: String,
    var data: DataCategory?
)

data class DataCategory(
    val categories: List<CategoryFields>
)

data class CategoryFields(
    var name: String,
    var id: Int
)