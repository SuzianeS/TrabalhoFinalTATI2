package com.example.quiz.network.services

import com.example.quiz.network.models.game.*
import retrofit2.Call
import retrofit2.http.*

interface GameService {
    @GET("games")
    @Headers("Content-Type: application/json")
    fun findOrCreate(
        @Query("difficulty") difficulty: String?,
        @Query("category_id") categoryId: Number?,
        @Header("Authorization") token: String
    ): Call<OutputFindOrCreateGame>

    @DELETE("games")
    @Headers("Content-Type: application/json")
    fun finish(@Header("Authorization") token: String): Call<OutputFinishGame>

    @GET("problems/next")
    @Headers("Content-Type: application/json")
    fun nextProblem(@Header("Authorization") token: String): Call<OutputNextProblem>

    @GET("problems/view")
    @Headers("Content-Type: application/json")
    fun currentProblem(@Header("Authorization") token: String): Call<OutputCurrentProblem>

    @POST("problems/answer")
    @Headers("Content-Type: application/json")
    fun sendAnswer(
        @Query("answer") answerId: Number,
        @Header("Authorization") token: String
    ): Call<OutputSendAnswer>
}