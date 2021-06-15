package com.example.quiz.network.services


import com.example.quiz.network.models.category.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoryService {
    @GET("categories")
    @Headers("Content-Type: application/json")
    fun findAll(): Call<Category>
}