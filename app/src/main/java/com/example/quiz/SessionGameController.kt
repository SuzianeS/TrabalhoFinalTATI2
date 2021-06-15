package com.example.quiz

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.quiz.database.AppDatabase
import com.example.quiz.database.daos.SessionDAO
import com.example.quiz.database.daos.SessionGameDAO
import com.example.quiz.network.models.Session
import com.example.quiz.network.models.SessionGame
import com.example.quiz.network.models.category.CategoryFields
import com.example.quiz.network.models.game.Game
import com.example.quiz.network.models.login.User
import com.example.quiz.network.models.login.UserFieldsLogin
import com.google.gson.Gson

class SessionGameController(context: Context) {

    companion object {
        var dao: SessionGameDAO? = null

        fun init(context: Context){
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user-db"
            )
                .allowMainThreadQueries()
                .build()

            dao = db.sessionGameDao()
        }

        fun reset(id: Int) {
            dao?.delete(id)
        }

        fun setGame(context: Context, game: Game) {
            Log.d("teste", game.toString())
            val t = SessionGame(game.difficulty, game.category.toString(), game.asserts, game.errors, game.score, game.category?.name!!,
                game.category?.id!!,  1)
            dao?.insert(t)
        }


        fun getGame(context: Context): Game {
            val session = dao?.get()
            val parse = CategoryFields(session?.categoria!!, session?.num)
            val game = Game(session?.difficulty, parse, session.asserts, session.errors, session.score)

            return game
        }

        fun setScore(context: Context, score: Int) {
            val res = getGame(context)
            val currentGameSettings = res
            currentGameSettings.score = score
            reset(1)
            setGame(context, currentGameSettings)
        }

        fun addError(context: Context) {
            val res = getGame(context)
            val currentGameSettings = res
            currentGameSettings.errors = currentGameSettings.errors + 1
            reset(1)
            setGame(context, currentGameSettings)
        }

        fun addAssert(context: Context) {
            val res = getGame(context)
            val currentGameSettings = res
            currentGameSettings.asserts = currentGameSettings.asserts + 1
            reset(1)
            setGame(context, currentGameSettings)
        }
    }
}