package com.example.quiz.dao

import android.util.Log
import com.example.quiz.network.models.login.OutputLogin
import com.example.quiz.network.models.login.User
import com.example.quiz.network.models.register.OutputRegister
import com.example.quiz.network.services.UsersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDAO {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(UsersService::class.java)

    fun insert(user: User, finished: (body: OutputRegister) -> Unit) {
        service.insert(user).enqueue(object : Callback<OutputRegister> {
            override fun onResponse(call: Call<OutputRegister>, response: Response<OutputRegister>) {
                val user = response.body()!!
                Log.d("teste", user.toString())
                finished(user)
            }
            override fun onFailure(call: Call<OutputRegister>, t: Throwable) {

            }
        })
    }

    fun logar(email: String, password: String, finished: (body: OutputLogin) -> Unit, error: () -> Unit) {
        service.auth(email, password).enqueue(object : Callback<OutputLogin> {
            override fun onFailure(call: Call<OutputLogin>, t: Throwable) {

            }
            override fun onResponse(call: Call<OutputLogin>, response: Response<OutputLogin>) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    finished(body)
                }else if(response.code() == 401){
                    error()
                }
            }
        })
    }
}