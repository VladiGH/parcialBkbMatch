package com.avgh.bkbcontrol.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "matchTable")
data class Match (
    @ColumnInfo(name = "teamOneName") val nameTeam1: String,
    @ColumnInfo(name = "teamTwoName") val nameTeam2: String,
    @ColumnInfo(name = "teamOneScore") val scoreTeam1: String,
    @ColumnInfo(name = "teamTwoScore") val scoreTeam2: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "time") val time: String

){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
