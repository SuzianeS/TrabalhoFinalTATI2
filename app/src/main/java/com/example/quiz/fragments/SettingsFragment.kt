package com.example.quiz.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz.*
import com.example.quiz.adapters.CategoryAdapter
import com.example.quiz.dao.CategoryDAO
import com.example.quiz.network.models.game.Game
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {

    private var daoCategory: CategoryDAO = CategoryDAO()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        view.btSave.setOnClickListener { saveSettings() }
        view.btLogout.setOnClickListener { logout() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daoCategory.findAll { categoriesAPI ->
            categoryAdapter = CategoryAdapter(categoriesAPI)
            view.rvCategory.adapter = categoryAdapter
            view.rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun saveSettings() {
        val game = Game(
            difficulty = "",
            category = categoryAdapter.getSelectedItem()
        )
        if (game.category?.name?.isNotEmpty()!!){
            val toast: Toast = Toast.makeText(activity, "Selected Category", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 10)
            toast.show()
            SessionGameController.setGame(requireActivity(), game)
        }else{

        }
    }

    private fun logout(){
        SessionController.logout(requireActivity(), 1)
        SessionGameController.reset(1)

        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
    }

}
