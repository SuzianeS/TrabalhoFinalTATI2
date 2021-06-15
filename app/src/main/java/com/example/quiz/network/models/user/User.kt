package com.example.quiz.network.models.login

data class User(
    var name: String,
    var password: String,
    var email: String,
    var token: String
){
    var id: Long? = null
}