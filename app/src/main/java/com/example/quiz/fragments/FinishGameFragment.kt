package com.example.quiz.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.quiz.ControllerGame
import com.example.quiz.R
import com.example.quiz.SessionGameController
import com.example.quiz.network.models.game.Game
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_finish_game.*
import kotlinx.android.synthetic.main.fragment_finish_game.view.*

class FinishGameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finish_game, container, false)
        view.btNew.setOnClickListener { newGame(view) }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadGameData()
    }

    private fun newGame(view: View){
        var navController: NavController? = null
        navController = Navigation.findNavController(view)
        navController!!.navigate(R.id.navegateToSettings)
    }

    private fun loadGameData() {
        val res = SessionGameController.getGame(requireActivity())
        val game = res
        Log.d("teste", game.toString())
        tvScore.text = game.score.toString()
        tvErrors.text = game.errors.toString()
        tvAsserts.text = game.asserts.toString()
        SessionGameController.reset(1)
    }
}