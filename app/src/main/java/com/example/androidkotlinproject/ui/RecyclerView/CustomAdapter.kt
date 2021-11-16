package com.example.androidkotlinproject.ui.RecyclerView

import MarvelResponse
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinproject.R
import com.squareup.picasso.Picasso
import fr.iem.model.MarvelCharacter

class CustomAdapter (private val dataSet: MarvelResponse, var clickCallBack : (marvelCharacter : MarvelCharacter) -> Unit):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.tvCharactername)
        val img : ImageView = view.findViewById(R.id.image)
        val textViewStatus: TextView = view.findViewById(R.id.tvCharacterstatus)
        init {

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
           clickCallBack(dataSet.data.results[position])
        }
        viewHolder.textViewName.text = dataSet.data.results[position].name
        viewHolder.textViewStatus.text = dataSet.data.results[position].modified
        var path : String = dataSet.data.results[position].thumbnail.path

        if (dataSet != null) {
            Picasso.get().load(path.replace("http","https")+"."+dataSet.data.results[position].thumbnail.extension).into(viewHolder.img)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.data.results.size
}