package com.example.quiz.network.models.register

data class OutputRegister (
    var status: String?,
    var message: String?,
    var data: DataRegister? //objeto JSON
)

data class DataRegister(
    val user: UserFieldsRegister?
)

data class UserFieldsRegister(
    val email: String?,
    val name: String?,
    val token: String?
)
