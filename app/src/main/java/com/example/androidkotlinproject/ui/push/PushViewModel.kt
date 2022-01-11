package com.example.androidkotlinproject.ui.push

import android.util.Log
import androidx.lifecycle.*
import com.example.androidkotlinproject.data.manager.APIManager
import kotlinx.coroutines.launch

class PushViewModel : ViewModel() {


   var name = liveData {

       emit(APIManager.marvelAPIByID(1017100).name)
    }
    var description = liveData {

        emit(APIManager.marvelAPIByID(1017100).description)
    }

    fun getName() : String{
        var name = "";

        viewModelScope.launch {

             name = APIManager.marvelAPIByID(1017100).name
        }
        return name
    }


}