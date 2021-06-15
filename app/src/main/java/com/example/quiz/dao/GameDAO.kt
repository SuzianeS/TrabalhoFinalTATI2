package com.example.quiz.dao

import com.example.quiz.network.models.game.*
import com.example.quiz.network.services.GameService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameDAO {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(GameService::class.java)

    fun findOrCreate(difficulty: String?, categoryId: Number?, token: String, finished: (user: OutputFindOrCreateGame) -> Unit) {
        service.findOrCreate(difficulty, categoryId, token).enqueue(object : Callback<OutputFindOrCreateGame> {
            override fun onResponse(call: Call<OutputFindOrCreateGame>, response: Response<OutputFindOrCreateGame>) {
                if (response.isSuccessful) {
                    val game = response.body()!!
                    finished(game)
                } else {
                   //error
                }
            }
            override fun onFailure(call: Call<OutputFindOrCreateGame>, t: Throwable) {
                //error
            }
        })
    }

    fun findNextProblem(token: String, finished: (user: OutputNextProblem) -> Unit, onError: (t: Throwable) -> Unit) {
        service.nextProblem(token).enqueue(object : Callback<OutputNextProblem> {
            override fun onResponse(call: Call<OutputNextProblem>, response: Response<OutputNextProblem>) {
                if (response.isSuccessful) {
                    val game = response.body()!!
                    finished(game)
                } else {
                    onError(Exception())
                }
            }
            override fun onFailure(call: Call<OutputNextProblem>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun findCurrentProblem(token: String, finished: (user: OutputCurrentProblem) -> Unit, onError: () -> Unit){
        service.currentProblem(token).enqueue(object : Callback<OutputCurrentProblem> {
            override fun onResponse(call: Call<OutputCurrentProblem>, response: Response<OutputCurrentProblem>) {
                if (response.isSuccessful) {
                    val problem = response.body()!!
                    finished(problem)
                } else {
                    if (response.code() == 400) {
                        onError()
                    }
                }

            }
            override fun onFailure(call: Call<OutputCurrentProblem>, t: Throwable) {

            }
        })
    }

    fun finishGame(token: String, finished: (user: OutputFinishGame) -> Unit) {
        service.finish(token).enqueue(object : Callback<OutputFinishGame> {
            override fun onResponse(call: Call<OutputFinishGame>, response: Response<OutputFinishGame>) {
                if (response.isSuccessful) {
                    val game = response.body()!!
                    finished(game)
                } else {
                    //error
                }
            }
            override fun onFailure(call: Call<OutputFinishGame>, t: Throwable) {
                //error
            }
        })
    }

    fun sendAnswer(answerId: Number, token: String, finished: (user: OutputSendAnswer) -> Unit) {
        service.sendAnswer(answerId, token).enqueue(object : Callback<OutputSendAnswer> {
            override fun onResponse(call: Call<OutputSendAnswer>, response: Response<OutputSendAnswer>) {
                if (response.isSuccessful) {
                    val res = response.body()!!
                    finished(res)
                }
            }
            override fun onFailure(call: Call<OutputSendAnswer>, t: Throwable) {

            }
        })
    }


}