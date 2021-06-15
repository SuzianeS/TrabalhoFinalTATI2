package com.example.quiz

import android.app.Activity
import android.content.Context
import com.example.quiz.network.models.game.Game
import com.example.quiz.network.models.login.User
import com.example.quiz.network.models.login.UserFieldsLogin
import com.google.gson.Gson

class ControllerGame {
    companion object {
        fun setAuthenticatedUser(context: Context, user: UserFieldsLogin) {
            val sharedPref = context.getSharedPreferences("_prefs_file_key", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                var userString = Gson().toJson(user)
                putString("_auth_user", userString)
                commit()
            }
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
            val sharedPref = context.getSharedPreferences("_prefs_file_key", Context.MODE_PRIVATE)
            val userAuth = sharedPref.getString("_auth_user", "")

            if (userAuth!!.isEmpty())
                throw Exception();
            return Gson().fromJson(userAuth, User::class.java)
        }

        fun logout(context: Context) {
            val sharedPref = context?.getSharedPreferences("_prefs_file_key", Context.MODE_PRIVATE)
            sharedPref.edit().remove("_auth_user").commit()
        }

        fun setGameSettings(context: Context, game: Game) {
            val sharedPref = context.getSharedPreferences("_prefs_file_game_key", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                var gameString = Gson().toJson(game)
                putString("_game", gameString)
                commit()
            }
        }

        fun getGame(context: Context): String {
            val sharedPref = context.getSharedPreferences("_prefs_file_game_key", Context.MODE_PRIVATE)
            val game = sharedPref.getString("_game", "")

            if (game!!.isEmpty()) {
                return ""
            }
            return game
        }






        fun reset(context: Context) {
            val sharedPref = context.getSharedPreferences("_prefs_file_game_key", Context.MODE_PRIVATE)
            sharedPref.edit().remove("_game").apply()
        }

        fun setScore(context: Context, score: Int) {
            val res = getGame(context)
            val currentGameSettings = Gson().fromJson(res, Game::class.java)
            currentGameSettings.score = score
            setGameSettings(context, currentGameSettings)
        }

        fun addError(context: Context) {
            val res = getGame(context)
            val currentGameSettings = Gson().fromJson(res, Game::class.java)
            currentGameSettings.errors = currentGameSettings.errors.toInt() + 1
            setGameSettings(context, currentGameSettings)
        }

        fun addAssert(context: Context) {
            val res = getGame(context)
            val currentGameSettings = Gson().fromJson(res, Game::class.java)
            currentGameSettings.asserts = currentGameSettings.asserts.toInt() + 1
            setGameSettings(context, currentGameSettings)
        }
    }
}

