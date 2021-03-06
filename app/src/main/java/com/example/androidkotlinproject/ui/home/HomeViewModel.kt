package com.example.androidkotlinproject.ui.home

import MarvelResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidkotlinproject.BuildConfig
import com.example.androidkotlinproject.data.manager.APIManager
import com.google.gson.Gson
import fr.iem.model.MarvelCharacter
import java.io.File

class HomeViewModel : ViewModel() {


   var name = liveData {
        val apiManager = APIManager()
       emit(apiManager.useMarvelAPI().name)
    }
    var description = liveData {
        val apiManager = APIManager()
        emit(apiManager.useMarvelAPI().description)
    }

    fun deleteText(){
       Log.d("nathan","buttonpushed")

    }
}