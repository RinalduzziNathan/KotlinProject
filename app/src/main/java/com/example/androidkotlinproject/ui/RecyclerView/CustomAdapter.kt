package com.example.androidkotlinproject.ui.RecyclerView

import MarvelResponse
import android.app.Activity
import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinproject.R
import com.example.androidkotlinproject.data.prefs.Prefs
import com.squareup.picasso.Picasso
import fr.iem.model.MarvelCharacter

class CustomAdapter (private val dataSet: MarvelResponse, var clickCallBack : (marvelCharacter : MarvelCharacter?, id : String?) -> Unit):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.tvCharactername)
        val img : ImageView = view.findViewById(R.id.image)
        val textViewStatus: TextView = view.findViewById(R.id.tvCharacterstatus)
        val btnFav: Button = view.findViewById(R.id.buttonfav)
        init{

        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


        viewHolder.itemView.setOnClickListener {
           clickCallBack(dataSet.data.results[position],null)
        }


        viewHolder.textViewName.text = dataSet.data.results[position].name
        viewHolder.textViewStatus.text = dataSet.data.results[position].modified

        //changer le nom du bouton suivant si l'id est en fav
        val prefs = Prefs(viewHolder.itemView.context)
        var listFav = prefs.myStringArray.toMutableList()
        dataSet.data.results.forEach{
            if(listFav.contains(it.id.toString())){
                Log.d("nathan","YES IT IS")
                viewHolder.btnFav.text = "unfav"
            }else
                viewHolder.btnFav.text = "fav"
        }

        var path : String = dataSet.data.results[position].thumbnail.path

        if (dataSet != null) {
            Picasso.get().load(path.replace("http","https")+"."+dataSet.data.results[position].thumbnail.extension).into(viewHolder.img)

            viewHolder.btnFav.setOnClickListener{

                clickCallBack(null,dataSet.data.results[position].id.toString())
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.data.results.size
}