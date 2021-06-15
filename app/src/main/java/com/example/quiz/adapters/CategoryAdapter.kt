package com.example.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.dao.CategoryDAO
import com.example.quiz.network.models.category.CategoryFields
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(category: List<CategoryFields>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val dao: CategoryDAO = CategoryDAO()
    private var categories: MutableList<CategoryFields> = mutableListOf()
    private var selected : CategoryFields? = null
    init {
        dao.findAll { cat ->
            categories = cat.toMutableList()
            notifyDataSetChanged()
        }
    }
    override fun getItemCount() = categories.size

    override fun getItemViewType(position: Int): Int {
        if (categories[position] == selected) {
            return R.layout.item_category_selected
        }
        return R.layout.item_category;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.fillView(category, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(category: CategoryFields, position: Int) {
            itemView.tvCategoryName.text = category.name

            itemView.setOnClickListener {
                selected = category
                notifyDataSetChanged()
            }
        }
    }

    fun getSelectedItem(): CategoryFields? {
        return selected
    }

}