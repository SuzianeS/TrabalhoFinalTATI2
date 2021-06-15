package com.example.quiz

import android.app.Activity
import android.content.Context
import androidx.room.Room
import com.example.quiz.database.AppDatabase
import com.example.quiz.database.daos.SessionDAO
import com.example.quiz.network.models.Session
import com.example.quiz.network.models.login.User
import com.example.quiz.network.models.login.UserFieldsLogin
import com.google.gson.Gson

class SessionController(context: Context) {

    companion object {
        var dao: SessionDAO? = null

        fun init(context: Context){
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user-db"
            )
                .allowMainThreadQueries()
                .build()

            dao = db.sessionDao()
        }

        fun logout(context: Context, id: Int) {
           dao?.delete(id)
        }

        fun setUser(context: Context, user: UserFieldsLogin) {
            val auth = Session(user.email, user.name, user.token, 1)
            dao?.insert(auth)
        }

        fun isAuthenticated(activity: Activity): Boolean {
            return try {
                getUser(activity)
                true
            } catch (e: Exception) {
                false
            }
        }

        fun getUser(context: Context): User {
            val session = dao?.get()
            val user = User(session?.name!!, "", session.email!!, session.token!!)
            return user
        }
    }
}