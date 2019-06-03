package com.avgh.bkbcontrol.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.avgh.bkbcontrol.AppConstants
import com.avgh.bkbcontrol.R
import com.avgh.bkbcontrol.database.Match
import com.avgh.bkbcontrol.databinding.ScoresBinding
import com.avgh.bkbcontrol.viewmodel.ViewModel
import com.avgh.bkbcontrol.viewmodel.matchViewModel
import kotlinx.android.synthetic.main.scores.*
import java.text.SimpleDateFormat
import java.util.*
class GamePlay: AppCompatActivity() {

    lateinit var scoreViewModel: ViewModel
    var fecha = Date()
    var formatFecha = SimpleDateFormat("dd-MM-yy")
    var formatHora = SimpleDateFormat("HH:mm")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ScoresBinding = DataBindingUtil.setContentView(this, R.layout.scores)
        val viewmodelBD = ViewModelProviders.of(this).get(matchViewModel::class.java)

        scoreViewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.viewmodel = scoreViewModel
        val getIntentNewGame: Intent = intent
        val teamA = getIntentNewGame.getStringExtra(AppConstants.KEY_TEAM1_NEWGAME_TO_THEGAME)
        val teamB = getIntentNewGame.getStringExtra(AppConstants.KEY_TEAM2_NEWGAME_TO_THEGAME)

        tv_teamA.text = teamA
        tv_teamB.text = teamB

        bt_finishgame.setOnClickListener {
            viewmodelBD.insert(Match(teamA,teamB,scoreViewModel.scoreTeamA.value!!,scoreViewModel.scoreTeamB.value!!,formatFecha.format(fecha).toString(),formatHora.format(fecha).toString()))
            val intent = Intent(this@GamePlay, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewmodelBD.getAll().observe(this, Observer {matches->
            //TODO: adapter.changeDataSet(repos)
            Log.d("LISTA DE REPOS", "------------------------------------")
            for(match in matches){
                Log.d("LISTA DE REPOS: ", match.nameTeam1)
                Log.d("LISTA DE REPOS: ", match.scoreTeam1)
                Log.d("LISTA DE REPOS: ", match.nameTeam2)
                Log.d("LISTA DE REPOS: ", match.scoreTeam2)
                Log.d("LISTA DE REPOS: ", match.date)
                Log.d("LISTA DE REPOS: ", match.time)
                Log.d("LISTA DE REPOS", "---------------------------------")
            }
        })


    }

    fun addPoints(score: String?, scoreNumPlus: Int): String {
        return (score?.toInt()?.plus(scoreNumPlus)).toString()
    }

    fun addOneTeamA(v: View) {
        scoreViewModel.scoreTeamA.value = addPoints(scoreViewModel.scoreTeamA.value, 1)
        Log.d("sumando en A 1: ", scoreViewModel.scoreTeamA.toString())
        //)
    }

    fun addOneTeamB(v: View) {
        scoreViewModel.scoreTeamB.value = addPoints(scoreViewModel.scoreTeamB.value, 1)
        Log.d("sumando en B 1: ", scoreViewModel.scoreTeamB.toString())
        //)
    }

    fun addTwoTeamA(v: View) {
        scoreViewModel.scoreTeamA.value = addPoints(scoreViewModel.scoreTeamA.value, 2)
        Log.d("sumando en A 2: ", scoreViewModel.scoreTeamA.toString())
    }

    fun addTwoTeamB(v: View) {
        scoreViewModel.scoreTeamB.value = addPoints(scoreViewModel.scoreTeamB.value, 2)
        Log.d("sumando en B 2: ", scoreViewModel.scoreTeamB.toString())
    }

    fun addThreeTeamA(v: View) {
        scoreViewModel.scoreTeamA.value = addPoints(scoreViewModel.scoreTeamA.value, 3)
        Log.d("sumando en A 3: ", scoreViewModel.scoreTeamA.toString())
    }

    fun addThreeTeamB(v: View) {
        scoreViewModel.scoreTeamB.value = addPoints(scoreViewModel.scoreTeamB.value, 3)
        Log.d("sumando en B 3: ", scoreViewModel.scoreTeamB.toString())
    }

    fun resetScores(v: View) {
        scoreViewModel.scoreTeamA.value = 0.toString()
        scoreViewModel.scoreTeamB.value = 0.toString()
    }
}