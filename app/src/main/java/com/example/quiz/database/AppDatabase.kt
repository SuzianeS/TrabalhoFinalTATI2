package com.example.quiz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quiz.database.daos.SessionDAO
import com.example.quiz.database.daos.SessionGameDAO
import com.example.quiz.network.models.Session
import com.example.quiz.network.models.SessionGame
@Database(entities = [Session::class, SessionGame::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun sessionDao(): SessionDAO
    abstract fun sessionGameDao(): SessionGameDAO
}