package com.example.quiz.network.models.ranking


data class Ranking(
    val status: String,
    val message: String,
    val data: DataRanking?
)

data class DataRanking(
    val ranking: List<UserScore>
)

data class UserScore(
    val user: String,
    val score: Number
)
