package com.example.androidkotlinproject.ui.home

import MarvelResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidkotlinproject.BuildConfig
import com.example.androidkotlinproject.data.manager.APIManager
import com.google.gson.Gson
import fr.iem.model.MarvelCharacter
import java.io.File

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = BuildConfig.API_PRIVATE_KEY
    }
    val text: LiveData<String> = _text

    suspend fun getMarvelData(){

        //call api with the interceptor
        val apiManager = APIManager()
        println(apiManager.useMarvelAPI().name)
    }
    /*
    fun parseJson () : MarvelCharacter {
        println("Parsing json :")
        val gson = Gson()

        val ironManString  = File("app/src/main/java/com/example/androidkotlinproject/data/resources/json","iron_man.json").readText()
        val ironMan : MarvelCharacter = gson.fromJson(ironManString, MarvelCharacter::class.java)

        val resultString = File("app/src/main/java/com/example/androidkotlinproject/data/resources/json","response.json").readText()
        val response : MarvelResponse = gson.fromJson(resultString, MarvelResponse::class.java)

        println("Data from response :  ${response.data.results[0].description}")

        return ironMan
    }
    val character : LiveData<MarvelCharacter> = MutableLiveData<MarvelCharacter>().apply {  parseJson()}

     */
}