package com.example.androidkotlinproject.repository

import MarvelResponse
import androidx.lifecycle.MutableLiveData
import com.example.androidkotlinproject.data.manager.APIManager
import fr.iem.model.MarvelCharacter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MarvelRepository {

    suspend fun fetchMarvelCharactersCoroutine(): MarvelResponse {

        var result = APIManager.marvelAPICharacters()

        return result
    }
    suspend fun fetchMarvelCharacterByIdCoroutine(id : Int): MarvelCharacter {

        var result = APIManager.marvelAPIByID(id)

        return result
    }

}