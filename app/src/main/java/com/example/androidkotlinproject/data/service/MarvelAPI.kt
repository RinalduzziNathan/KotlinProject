package com.example.androidkotlinproject.data.service

import MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MarvelAPI  {
    @GET("/v1/public/characters/{character_id}")
    suspend fun getCharacterByID(
        @Path("character_id") characterID : Int,
    ): Response<MarvelResponse>

}