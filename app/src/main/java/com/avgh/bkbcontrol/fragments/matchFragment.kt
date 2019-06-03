package com.avgh.bkbcontrol.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.avgh.bkbcontrol.R
import com.avgh.bkbcontrol.database.Match

class matchFragment: Fragment() {
    lateinit var match: Match

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.match_viewer, container, false)
        setData(match, view)
        return view
    }

    fun setData(myMatch: Match, view: View){
        view.findViewById<TextView>(R.id.tv_team_name_left).text = myMatch.nameTeam1
        view.findViewById<TextView>(R.id.tv_team_score_left).text = myMatch.scoreTeam1
        view.findViewById<TextView>(R.id.tv_team_name_right).text = myMatch.nameTeam2
        view.findViewById<TextView>(R.id.tv_team_score_right).text = myMatch.scoreTeam2
        view.findViewById<TextView>(R.id.tv_fecha).text = myMatch.date
        view.findViewById<TextView>(R.id.tv_hora).text = myMatch.time
    }

    companion object {
        @JvmStatic
        fun newInstance(match: Match):matchFragment{
            val newFragment= matchFragment()
            newFragment.match= match
            return newFragment

        }
    }

}