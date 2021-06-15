package com.example.quiz.network.services

import com.example.quiz.network.models.ranking.Ranking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RankingService {

    @GET("ranking")
    @Headers("Content-Type: Application/json")
    fun getAll(): Call<Ranking>
}