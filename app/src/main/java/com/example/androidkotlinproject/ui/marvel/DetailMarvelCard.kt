package com.example.androidkotlinproject.ui.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidkotlinproject.R
import com.example.androidkotlinproject.data.prefs.Prefs
import com.squareup.picasso.Picasso
import fr.iem.model.MarvelCharacter

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
                    supportActionBar?.title = "Detail : "+it.name
                    textViewName.text = it.name
                    textViewDescription.text = it.description

                    Picasso.get().load(it.thumbnail.path.replace("http","https")+"."+it.thumbnail.extension).into(imageViewDetail)
                    val buttonFav = findViewById<Button>(R.id.buttonFavDetails)
                    val prefs = Prefs(this.applicationContext)

                    var listVarCard = mutableListOf<MarvelCharacter>()
                    var listFav = prefs.myStringArray.toMutableList()

                    if (listFav.contains(it.id.toString())) {
                        buttonFav.text = "Retirer Favoris"
                    } else{
                        buttonFav.text = "Ajouter Favoris"
                    }
                    val marvelChar = it
                    buttonFav.setOnClickListener() {
                        listFav = prefs.myStringArray.toMutableList()
                        if(listFav.contains(marvelChar.id.toString())){
                            listFav.remove(marvelChar.id.toString())
                            buttonFav.text = "Ajouter Favoris"
                        }else{
                            listFav.add(marvelChar.id.toString())
                            buttonFav.text = "Retirer Favoris"
                        }
                        prefs.myStringArray = listFav.toTypedArray()
                    }
                })
        }



    }


}