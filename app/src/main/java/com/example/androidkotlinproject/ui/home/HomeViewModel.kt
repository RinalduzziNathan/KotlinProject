package com.example.androidkotlinproject.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.androidkotlinproject.data.manager.APIManager
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


   var name = liveData {
        val apiManager = APIManager()
       emit(apiManager.marvelAPIByID(1017100).name)
    }
    var description = liveData {
        val apiManager = APIManager()
        emit(apiManager.marvelAPIByID(1017100).description)
    }

    fun getName() : String{
        var name = "";

        viewModelScope.launch {
            val apiManager = APIManager()
             name = apiManager.marvelAPIByID(1017100).name
        }
        return name
    }

    fun deleteText(){
       Log.d("nathan","buttonpushed")

    }
}