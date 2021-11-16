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

class CustomAdapter(private val dataSet: MarvelCharacter) :
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
            // Define click listener for the ViewHolder's View.
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

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewName.text = dataSet.name
        viewHolder.textViewStatus.text = dataSet.modified

        var path : String = dataSet.thumbnail.path
        if (dataSet != null) {
            Picasso.get().load(path.replace("http","https")+"."+dataSet.thumbnail.extension).into(viewHolder.img)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = 20
}