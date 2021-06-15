package com.example.quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz.ControllerGame

import com.example.quiz.R
import com.example.quiz.SessionController
import com.example.quiz.SessionGameController
import com.example.quiz.adapters.AnswerAdapter
import com.example.quiz.dao.GameDAO
import com.example.quiz.network.models.SessionGame
import com.example.quiz.network.models.game.Game
import com.example.quiz.network.models.game.OutputCurrentProblem
import com.example.quiz.network.models.game.OutputNextProblem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment(), View.OnClickListener {

    private val dao: GameDAO = GameDAO()
    private lateinit var answerAdapter: AnswerAdapter
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        btnFinishGame.setOnClickListener(this)
        btnSendAnswer.setOnClickListener(this)
        btnNextProblem.setOnClickListener(this)

        restartGame()
    }

    fun restartGame() {
        progressBarGame.visibility = View.VISIBLE
        setVisibleAllItems(View.GONE)
        enableClickOnButtons(false)
        btnNextProblem.visibility = View.GONE
        progressBarAnswer.visibility = View.GONE

        findOrCreateGame()
    }

    private fun findOrCreateGame() {
        //val user = ControllerGame.getUser(requireActivity())
        val user = SessionController.getUser(requireActivity())
        val game = SessionGameController.getGame(requireActivity())
        dao.findOrCreate(game.difficulty, game.category?.id, user.token) { dataAPI ->
            if (dataAPI.data?.game?.creation == "new_game") {
                nextProblem()
            } else if (dataAPI.data?.game?.creation == "existing_game") {
                currentProblem()
            }else{
            }
        }
    }

    private fun currentProblem(){
        val user = SessionController.getUser(requireActivity())
        dao.findCurrentProblem(user.token, { problemAPI ->
            setCurrentProblem(problemAPI)
        }, {
            nextProblem()
        })
    }

    private fun nextProblem() {
        btnNextProblem.visibility = View.GONE
        progressBarAnswer.visibility = View.GONE
        val user = SessionController.getUser(requireActivity())
        dao.findNextProblem(user.token, { problemAPI ->
            setNextProblem(problemAPI)
        }, {
            progressBarGame.visibility = View.GONE
        })
    }

    private fun setNextProblem(problem: OutputNextProblem) {
        val response = problem.data?.problem?.question!!.toString()
        tvProblem.text = response

        answerAdapter = AnswerAdapter(problem.data?.problem?.answers!!)
        listAnswers.adapter = answerAdapter
        listAnswers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setVisibleAllItems(View.VISIBLE)
        progressBarGame.visibility = View.GONE
        enableClickOnButtons(true)
    }

    private fun setCurrentProblem(problem: OutputCurrentProblem) {
        tvProblem.text = problem.data?.problem?.question!!.toString()

        answerAdapter = AnswerAdapter(problem.data?.problem?.answers!!)
        listAnswers.adapter = answerAdapter
        listAnswers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setVisibleAllItems(View.VISIBLE)
        progressBarGame.visibility = View.GONE
        enableClickOnButtons(true)
    }

    private fun setVisibleAllItems(visible: Int) {
        btnSendAnswer.visibility = visible
        btnFinishGame.visibility = visible
        tvProblem.visibility = visible
        listAnswers.visibility = visible
    }

    private fun enableClickOnButtons(enable: Boolean) {
        btnSendAnswer.isEnabled = enable
        btnFinishGame.isEnabled = enable
    }

    private fun setVisibleButtons(visible: Int) {
        btnSendAnswer.visibility = visible
        btnFinishGame.visibility = visible
    }

    private fun finishGame() {
        val user = SessionController.getUser(requireActivity())

        dao.finishGame(user.token) { gameAPI ->
            navController!!.navigate(R.id.action_gameFragment_to_finishGameFragment)
        }
    }

    private fun sendAnswer() {
        val user = SessionController.getUser(requireActivity())

        val selectAnswer = answerAdapter.getSelectedItem() ?: return

        enableClickOnButtons(false)
        setVisibleButtons(View.GONE)

        progressBarAnswer.visibility = View.VISIBLE

        dao.sendAnswer(selectAnswer!!.order, user.token) { res ->
            if (res.data.answer.isCorrect()) {
                answerAdapter.setCorrect(selectAnswer)
                SessionGameController.addAssert(requireActivity())
            } else {
                answerAdapter.setError(selectAnswer)
                answerAdapter.setCorrect(res.data.answer.correctAnswer)
                SessionGameController.addError(requireActivity())
            }
            SessionGameController.setScore(requireActivity(), res.data.answer.score)

            btnNextProblem.visibility = View.VISIBLE
            progressBarAnswer.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSendAnswer -> {
                sendAnswer()
            }
            R.id.btnFinishGame -> {
                finishGame()
            }
            R.id.btnNextProblem -> {
                nextProblem()
            }
            else -> {
            }
        }
    }

}
