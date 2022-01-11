package com.example.androidkotlinproject.ui.marvel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidkotlinproject.repository.MarvelRepository
import fr.iem.model.MarvelCharacter
import kotlinx.coroutines.launch

class DetailMarvelCardViewModel : ViewModel() {


    fun detailLiveData(id : Int) : MutableLiveData<MarvelCharacter> {


        val data = MutableLiveData<MarvelCharacter>()

        viewModelScope.launch {
            data.postValue(MarvelRepository.fetchMarvelCharacterByIdCoroutine(id))
        }

        return data
    }
}