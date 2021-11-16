package com.example.androidkotlinproject.data.manager
import MarvelResponse
import com.example.androidkotlinproject.BASE_URL
import com.google.gson.Gson

import com.example.androidkotlinproject.data.interceptor.InterceptorMarvelAPI
import fr.iem.model.MarvelCharacter
import com.example.androidkotlinproject.data.service.MarvelAPI

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.lang.IllegalStateException


class APIManager {
    suspend fun marvelAPIByID() : MarvelCharacter {

        //create an instance of our interceptor
        val interceptor = InterceptorMarvelAPI()
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        // link the interceptor with retrofit instance
        val retrofit =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(MarvelAPI::class.java)

        val response = retrofit.getCharacterByID(
            characterID = 1009368,
        )

        val ironMan = if(response.isSuccessful){
            response.body()?.data?.results
                ?: throw IllegalStateException()
        } else throw IllegalStateException("Response Code is ${response.code()}")

        return ironMan[0]
    }
    suspend fun marvelAPICharacters() : MarvelResponse {

        //create an instance of our interceptor
        val interceptor = InterceptorMarvelAPI()
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        // link the interceptor with retrofit instance
        val retrofit =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(MarvelAPI::class.java)

        val response = retrofit.getCharacters()


        val ironMan = if(response.isSuccessful){
            response.body()
                ?: throw IllegalStateException()
        } else throw IllegalStateException("Response Code is ${response.code()}")

        return ironMan
    }
}