package com.example.quiz.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quiz.network.models.SessionGame

@Dao
interface SessionGameDAO {

    @Query("SELECT * FROM game")
    fun get(): SessionGame

    @Insert
    fun insert(user: SessionGame): Long

    @Query("DELETE FROM game Where logado IN (:ids)")
    fun delete(ids: Int)
}