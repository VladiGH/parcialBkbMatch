package com.avgh.bkbcontrol

import com.avgh.bkbcontrol.database.Match

object AppConstants {
    val KEY_TEAM1_NEWGAME_TO_THEGAME = "key_nombre1"
    val KEY_TEAM2_NEWGAME_TO_THEGAME = "key_nombre2"
}

interface updateList {
    fun changeDataSet(newDataSet : List<Match>)
}