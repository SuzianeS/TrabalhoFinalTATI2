package com.example.quiz.network.models.login


data class OutputLogin(
    //estrutruração da resposta
    var status: String?,
    var message: String?,
    var data: DataLogin? //objeto JSON
)

data class DataLogin(
    val user: UserFieldsLogin? //bloco JSON do user
)

data class UserFieldsLogin( //estruturação do bloco user
    val email: String?,
    val name: String?,
    val token: String?
)