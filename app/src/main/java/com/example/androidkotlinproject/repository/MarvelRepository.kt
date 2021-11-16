package com.example.androidkotlinproject.repository

import MarvelResponse
import androidx.lifecycle.MutableLiveData
import com.example.androidkotlinproject.data.manager.APIManager
import fr.iem.model.MarvelCharacter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MarvelRepository {

    suspend fun fetchMarvelCharactersCoroutine(): MutableList<MarvelCharacter> {

        val list = mutableListOf<MarvelCharacter>()

        val apiManager = APIManager()
        var result = apiManager.marvelAPICharacters()

        result.data.results?.let {
            list.addAll(it)
        }

        return list
    }

}