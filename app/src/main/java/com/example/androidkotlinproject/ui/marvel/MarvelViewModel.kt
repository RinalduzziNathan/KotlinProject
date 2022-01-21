package com.example.androidkotlinproject.ui.marvel

import MarvelResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidkotlinproject.repository.MarvelRepository
import kotlinx.coroutines.launch

class MarvelViewModel : ViewModel() {


    fun recyclerLiveData() : MutableLiveData<MarvelResponse> {


        val data = MutableLiveData<MarvelResponse>()

        viewModelScope.launch {

            data.postValue(MarvelRepository.fetchMarvelCharactersCoroutine())
        }

        return data
    }
}