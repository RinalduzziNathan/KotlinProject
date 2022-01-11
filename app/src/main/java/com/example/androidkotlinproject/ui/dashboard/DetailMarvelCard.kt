package com.example.androidkotlinproject.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidkotlinproject.R
import com.squareup.picasso.Picasso

class DetailMarvelCard : AppCompatActivity() {


    private lateinit var detailMarvelCardViewModel: DetailMarvelCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_marvel_card)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val textViewName = findViewById<TextView>(R.id.nameDetail)
        val imageViewDetail = findViewById<ImageView>(R.id.imageDetail)
        val textViewDescription = findViewById<TextView>(R.id.descriptionDetail)
        detailMarvelCardViewModel = ViewModelProvider(this).get(DetailMarvelCardViewModel::class.java)

        if (message != null) {
             detailMarvelCardViewModel.detailLiveData(message.toInt()).observe(this,
                Observer {
                    textViewName.text = it.name
                    textViewDescription.text = it.description
                    Picasso.get().load(it.thumbnail.path.replace("http","https")+"."+it.thumbnail.extension).into(imageViewDetail)
                })
        }
    }


}