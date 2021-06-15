package com.example.quiz.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.network.models.problem.Answer
import kotlinx.android.synthetic.main.item_answer.view.*

class AnswerAdapter(answersList: List<Answer>) : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    private var answers = mutableListOf<Answer>()
    private var selected : Answer? = null
    private var correct : Answer? = null
    private var error: Answer? = null

    init {
        answers = answersList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = answers.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_answer;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = answers[position]
        holder.fillView(answer, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceAsColor")
        fun fillView(answer: Answer, position: Int) {
            itemView.tvAnswer.text = answer.order.toString() + ") " + answer.description

            when (answer) {
                correct -> {
                    itemView.setBackgroundColor(Color.GREEN)
                }
                error -> {
                    itemView.setBackgroundColor(Color.RED)
                }
                selected -> {
                    if (!hasBeenValidated())
                        itemView.setBackgroundColor(Color.parseColor("#00574B"))
                }
                else -> {
                    itemView.setBackgroundColor(Color.parseColor("#008577"))
                }
            }

            itemView.setOnClickListener {
                selected = answer
                notifyDataSetChanged()
            }
        }
    }

    fun hasBeenValidated() : Boolean {
        return correct != null
    }

    fun getSelectedItem(): Answer? {
        return selected
    }

    fun setCorrect(answer: Answer) {
        this.correct = answer
        this.selected = null
        notifyDataSetChanged()
    }

    fun setError(answer: Answer) {
        this.error = answer
        this.selected = null
        notifyDataSetChanged()
    }
}