package com.example.androidkotlinproject.data.manager

import java.io.File
import java.io.FileInputStream
import java.util.*

class ResourceManager {

    fun readMarvelProperties(): Properties {
        val properties = Properties()
    //    properties.load(FileInputStream(File("src/main/resources/properties/marvel_api_key.properties")))
        return properties
    }
}