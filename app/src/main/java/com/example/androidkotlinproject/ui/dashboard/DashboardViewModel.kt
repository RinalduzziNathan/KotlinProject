package com.example.androidkotlinproject.ui.dashboard

import MarvelResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidkotlinproject.repository.MarvelRepository
import fr.iem.model.MarvelCharacter
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }


    fun recyclerLiveData() : MutableLiveData<MarvelCharacter> {


        val data = MutableLiveData<MarvelCharacter>()

        viewModelScope.launch {
            data.postValue(MarvelRepository.fetchMarvelCharactersCoroutine()[0])
        }

        return data
    }
}