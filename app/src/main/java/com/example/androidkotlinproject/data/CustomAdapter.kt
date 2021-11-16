package com.example.android3

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

class CustomAdapter(private val dataSet: MarvelResponse) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvCharacterstatus)
        val img : ImageView = view.findViewById(R.id.image)

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
        viewHolder.textView.text = dataSet.data.results[position].name
            //viewHolder.img
        //if (dataSet != null) {
          //  Picasso.get().load(dataSet.get(position).thumbnail.path+dataSet.get(position).thumbnail.extension).into(viewHolder.img)
        //}
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.data.results.size
}