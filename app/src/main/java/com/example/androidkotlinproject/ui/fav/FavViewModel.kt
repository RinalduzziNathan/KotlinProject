package com.example.androidkotlinproject.ui.fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidkotlinproject.data.prefs.Prefs

class FavViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"

    }
    val text: LiveData<String> = _text
}