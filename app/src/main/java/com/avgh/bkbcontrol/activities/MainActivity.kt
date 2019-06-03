package com.avgh.bkbcontrol.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.avgh.bkbcontrol.R
import com.avgh.bkbcontrol.adapters.MatchAdapter
import com.avgh.bkbcontrol.database.Match
import com.avgh.bkbcontrol.fragments.MatchListFragment
import com.avgh.bkbcontrol.fragments.matchFragment
import com.avgh.bkbcontrol.viewmodel.matchViewModel
import kotlinx.android.synthetic.main.historial_games.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MatchListFragment.MatchListener{

    private lateinit var mainFragment: MatchListFragment
    private lateinit var matchFragmentContent: matchFragment
    private var listOfMatches = arrayListOf<Match>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addGame.setOnClickListener{
            startActivity(Intent(this, NewGame::class.java))

        }

        val viewModel = ViewModelProviders.of(this).get(matchViewModel::class.java)

        viewModel.getAll().observe(this, Observer {
            matches ->

            Log.d("Lista de partidos","-----------------------")
            for (match in matches){
                //listOfMatches.add(match)


                Log.d("Lista: ", match.nameTeam1)
                Log.d("Lista: ", match.nameTeam2)
            }
        })

        initMainFragment()
    }


    override fun managePortraitItemClick(match: Match) {
        matchFragmentContent = matchFragment.newInstance(match)
        changeFragment(R.id.main_fragment_container, matchFragmentContent)
    }

    override fun manageLandscapeItemClick(match: Match) {
        matchFragmentContent = matchFragment.newInstance(match)
        changeFragment(R.id.fragment_content, matchFragmentContent)

    }

    fun initMainFragment(){

        mainFragment = MatchListFragment.newInstance(listOfMatches)

        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment_container
        else{
            matchFragmentContent = matchFragment.newInstance(Match("N/A","N/A","N/A","N/A","N/A","N/A"))
            changeFragment(R.id.fragment_content, matchFragmentContent)

            R.id.landscape_left
        }

       changeFragment(resource, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }
}
