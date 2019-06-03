package com.avgh.bkbcontrol.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MatchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)//TODO: si esta repetido algo para eso es lo de onConflict
        suspend fun insert(matchs: Match)

    @Query("SELECT*FROM matchTable")
    fun getAllMatches(): LiveData<List<Match>>

    @Query("DELETE FROM matchTable")
    fun deleteTable()

}