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

    var match = Match("N/A","N/A","N/A","N/A","N/A","N/A")

    companion object {
        @JvmStatic
        fun newInstance(match: Match):matchFragment{
            val newFragment= matchFragment()
            newFragment.match= match
            return newFragment

        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.match_viewer, container, false)
        setData(view)
        return view
    }

    fun setData(view: View){
        view.findViewById<TextView>(R.id.tv_team_name_left).text = match.nameTeam1
        view.findViewById<TextView>(R.id.tv_team_score_left).text = match.scoreTeam1
        view.findViewById<TextView>(R.id.tv_team_name_right).text = match.nameTeam2
        view.findViewById<TextView>(R.id.tv_team_score_right).text = match.scoreTeam2
        view.findViewById<TextView>(R.id.tv_fecha).text = match.date
        view.findViewById<TextView>(R.id.tv_hora).text = match.time
        view.findViewById<TextView>(R.id.winner).text = isWinner(match)
    }
    fun isWinner(match: Match): String{
        if(match.scoreTeam1 > match.scoreTeam2)
            return match.nameTeam1

        if(match.scoreTeam2 > match.scoreTeam1)
            return match.nameTeam2
        if(match.scoreTeam1 == match.scoreTeam2)
            return "Ninguno. ¡E M P A T E!"
        else
            return "Pos nada"
    }




}