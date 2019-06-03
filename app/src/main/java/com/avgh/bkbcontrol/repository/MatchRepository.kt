package com.avgh.bkbcontrol.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.avgh.bkbcontrol.database.Match
import com.avgh.bkbcontrol.database.MatchDAO

class MatchRepository(private val matchDao: MatchDAO) {
    @WorkerThread
    suspend fun insert(match: Match){
        matchDao.insert(match)
    }

    fun getAll(): LiveData<List<Match>> {
        return matchDao.getAllMatches()
    }

    @WorkerThread
    suspend fun nuke(){
        return matchDao.deleteTable()
    }

}