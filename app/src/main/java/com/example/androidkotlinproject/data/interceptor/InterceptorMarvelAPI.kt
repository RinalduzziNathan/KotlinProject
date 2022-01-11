package com.example.androidkotlinproject.data.interceptor

import android.util.Log
import com.example.androidkotlinproject.BuildConfig
import com.example.androidkotlinproject.data.manager.HashMd5Manager
import com.example.androidkotlinproject.data.manager.ResourceManager

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

class InterceptorMarvelAPI : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val resourceManager = ResourceManager()
       // val marvelApiKeyProperties = resourceManager.readMarvelProperties()

        val publicKey = BuildConfig.API_PUBLIC_KEY
        val privateKey = BuildConfig.API_PRIVATE_KEY

        val timestamp = Date().time

        val hashMd5Manager = HashMd5Manager()

        val hash = hashMd5Manager.useMD5("$timestamp$privateKey$publicKey")
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder().addQueryParameter("ts",timestamp.toString())
            .addQueryParameter("apikey",publicKey)
            .addQueryParameter("hash",hash)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }


}