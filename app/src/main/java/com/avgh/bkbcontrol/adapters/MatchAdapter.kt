package com.avgh.bkbcontrol.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.avgh.bkbcontrol.R
import com.avgh.bkbcontrol.database.Match
import com.avgh.bkbcontrol.updateList
import kotlinx.android.synthetic.main.games_resume.view.*

class MatchAdapter(var matches: List<Match>, val clickListener: (Match)-> Unit ) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>(), updateList {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.games_resume, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return matches.size
    }

    override fun changeDataSet(newDataSet: List<Match>) {
        this.matches = newDataSet
        notifyDataSetChanged()
    }

    fun changeDataSetMatches(newDataSet: List<Match>) {
        this.matches = newDataSet
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matches[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: Match, clickListener: (Match) -> Unit) = with (itemView){
            tv_team_name_left.text = item.nameTeam1
            tv_team_score_left.text = item.scoreTeam1
            tv_team_name_right.text = item.nameTeam2
            tv_team_score_right.text = item.scoreTeam2
            this.setOnClickListener { clickListener(item) }
        }
    }
}