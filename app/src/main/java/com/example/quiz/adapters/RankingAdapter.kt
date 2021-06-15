package com.example.quiz.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.dao.RankingDAO
import com.example.quiz.network.models.ranking.UserScore
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class RankingAdapter(view: View): RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    private val dao: RankingDAO = RankingDAO()
    private var ranking: MutableList<UserScore> = mutableListOf()

    init {
        dao.getRanking { usersRankingAPI ->
            ranking = usersRankingAPI.toMutableList()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return ranking.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = ranking[position]
        holder.fillView(user, position)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun fillView(user: UserScore, position: Int){
            if(position == 0 || position == 1 || position == 2){
                itemView.card.setBackgroundColor(Color.parseColor("#ffffbb33"))
            }
            itemView.name.text = (position + 1).toString() + "º"+ " " + user.user
            itemView.score.text = "Score: " + user.score.toString()
        }
    }

}