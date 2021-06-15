package com.example.quiz.dao

import android.util.Log
import com.example.quiz.network.models.ranking.Ranking
import com.example.quiz.network.models.ranking.UserScore
import com.example.quiz.network.services.RankingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RankingDAO {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(RankingService::class.java)

    fun getRanking(finished: (user: List<UserScore>) -> Unit) {
        service.getAll().enqueue(object : Callback<Ranking> {
            override fun onResponse(call: Call<Ranking>, response: Response<Ranking>) {
                val users = response.body()!!.data?.ranking
                if (users != null) {
                    finished(users)
                }
            }

            override fun onFailure(call: Call<Ranking>, t: Throwable) {
                Log.d("teste", "erro 2")
                //mensagem de erro
            }
        })
    }
}
