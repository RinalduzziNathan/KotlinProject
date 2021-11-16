package com.example.androidkotlinproject.ui.home

import MarvelResponse
import android.util.Log
import androidx.lifecycle.*
import com.example.androidkotlinproject.BuildConfig
import com.example.androidkotlinproject.data.manager.APIManager
import com.google.gson.Gson
import fr.iem.model.MarvelCharacter
import kotlinx.coroutines.launch
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

    fun getName() : String{
        var name = "";

        viewModelScope.launch {
            val apiManager = APIManager()
             name = apiManager.useMarvelAPI().name
        }
        return name
    }

    fun deleteText(){
       Log.d("nathan","buttonpushed")

    }
}