package com.example.quiz.database.daos

import androidx.room.*
import com.example.quiz.network.models.Session
import com.example.quiz.network.models.login.User

@Dao
interface SessionDAO {

    @Query("SELECT * FROM sessionGame")
    fun getAll(): List<Session>

    @Query("SELECT * FROM sessionGame")
    fun get(): Session

    @Query("SELECT * FROM sessionGame WHERE id IN (:ids)")
    fun getAllByIds(ids: IntArray): List<Session>

    @Query("SELECT * FROM sessionGame WHERE name LIKE :name AND email LIKE :email")
    fun findByName(name: String, email: String): Session

    @Insert
    fun insert(user: Session): Long

    @Query("DELETE FROM sessionGame Where logado IN (:ids)")
    fun delete(ids: Int)
}