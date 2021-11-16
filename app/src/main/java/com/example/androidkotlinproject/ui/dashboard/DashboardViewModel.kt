package com.example.androidkotlinproject.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidkotlinproject.data.manager.APIManager

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val recyclerLiveData = liveData {

        val apiManager = APIManager()
        emit(apiManager.useMarvelAPIResponse())
    }
}