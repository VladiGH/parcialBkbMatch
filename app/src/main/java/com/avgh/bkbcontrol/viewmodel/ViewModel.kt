package com.avgh.bkbcontrol.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    val scoreTeamA = MutableLiveData<String>()
    val scoreTeamB = MutableLiveData<String>()

    init{
        scoreTeamA.value = "0"
        scoreTeamB.value = "0"
    }
}