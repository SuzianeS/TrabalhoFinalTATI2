package com.example.quiz.network.services

import com.example.quiz.network.models.login.OutputLogin
import com.example.quiz.network.models.login.User
import com.example.quiz.network.models.register.OutputRegister

import retrofit2.Call
import retrofit2.http.*

interface UsersService {

    @POST("users")
    @Headers("Content-Type: Application/json")
    fun insert(@Body user: User): Call<OutputRegister>

    @POST("auth")
    @Headers("Content-Type: application/json")
    fun auth(@Query("email") username: String, @Query("password") password: String): Call<OutputLogin>

}