package com.avgh.bkbcontrol.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.avgh.bkbcontrol.AppConstants
import com.avgh.bkbcontrol.R
import com.avgh.bkbcontrol.adapters.MatchAdapter
import com.avgh.bkbcontrol.database.Match
import com.avgh.bkbcontrol.updateList
import com.avgh.bkbcontrol.viewmodel.matchViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.games.view.*
import kotlinx.android.synthetic.main.historial_games.*

class MatchListFragment: Fragment() {

    private var listOfMatches = arrayListOf<Match>()
    private lateinit var matchAdapter: updateList

    var listener: MatchListener?=null

    companion object{
        fun newInstance(dataset: ArrayList<Match>): MatchListFragment{
            val newFragment = MatchListFragment()
            newFragment.listOfMatches = dataset
            return newFragment
        }
    }
    interface MatchListener{
        fun managePortraitItemClick(match: Match)
        fun manageLandscapeItemClick(match: Match)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.games, container, false)
        val viewModel = ViewModelProviders.of(this).get(matchViewModel::class.java)

        viewModel.getAll().observe(this, Observer {
            matches ->

            Log.d("Lista de partidos","-----------------------")
            for (match in matches){
                listOfMatches.add(match)
                matchAdapter.changeDataSet(listOfMatches)

            }
        })
        initRecyclerView(resources.configuration.orientation, view)
        return view
    }

    fun initRecyclerView(orientation:Int, container:View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            matchAdapter = MatchAdapter(listOfMatches, {match: Match -> listener?.managePortraitItemClick(match)})
            container.rv_finished_matches.adapter = matchAdapter as MatchAdapter
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            matchAdapter = MatchAdapter(listOfMatches, {match: Match -> listener?.manageLandscapeItemClick(match)})
            container.rv_finished_matches.adapter = matchAdapter as MatchAdapter
        }
        container.rv_finished_matches.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }


    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MatchListener) {
            listener = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz")
        }
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}