package com.example.androidkotlinproject.ui.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinproject.R



class ContactsAdapter( var clickCallBack : (id : Int) -> Unit ) : androidx.recyclerview.widget.RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {




    // Define the listener interface
    interface OnItemClickListener {
        fun onItemClick(itemView: View?, position: Int)
    }

    // Define listener member variable
    private lateinit var listener: OnItemClickListener

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = itemView.findViewById(R.id.tvCharactername)
        val tvHometown: TextView = itemView.findViewById(R.id.tvCharacterstatus)

        init {
            // Setup the click listener
            itemView.setOnClickListener {
                // Triggers click upwards to the adapter on click
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(itemView, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: kotlin.Int
    ): com.example.androidkotlinproject.ui.RecyclerView.ContactsAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: com.example.androidkotlinproject.ui.RecyclerView.ContactsAdapter.ViewHolder,
        position: kotlin.Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): kotlin.Int {
        TODO("Not yet implemented")
    }

    // ...
}