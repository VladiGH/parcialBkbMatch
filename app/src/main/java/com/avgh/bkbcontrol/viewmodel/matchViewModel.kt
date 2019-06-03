package com.avgh.bkbcontrol.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.avgh.bkbcontrol.database.Match
import com.avgh.bkbcontrol.database.RoomDB
import com.avgh.bkbcontrol.repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class matchViewModel(app:Application): AndroidViewModel(app){

    private val repository: MatchRepository

    init{
        val matchDao = RoomDB.getInstance(app).matchDao()
        repository = MatchRepository(matchDao)
    }

    fun insert(match: Match)= viewModelScope.launch(Dispatchers.IO) {
        repository.insert(match)
    }

    fun getAll(): LiveData<List<Match>> {
        return repository.getAll()
    }
}