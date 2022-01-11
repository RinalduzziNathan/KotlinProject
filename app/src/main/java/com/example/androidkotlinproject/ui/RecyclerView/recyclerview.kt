package com.example.androidkotlinproject.ui.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinproject.R
import com.squareup.picasso.Picasso


class MyRecyclerViewAdapter internal constructor(
    context: Context?,
    data: List<String>,
    data2: List<String>,
    dataImageString: List<String>
) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    private val mDataImage: List<ImageView>? = null
    private val mDataImageString: List<String>
    private val mData: List<String>
    private val mData2: List<String>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.view, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = mData[position]
        val status = mData2[position]

        holder.myTextView.text = name
        holder.myTextView2.text = status
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var myTextView: TextView
        var myTextView2: TextView
        var myImgView: ImageView
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            myTextView = itemView.findViewById(R.id.tvCharactername)
            myTextView2 = itemView.findViewById(R.id.tvCharacterstatus)
            myImgView = itemView.findViewById(R.id.image)
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return mData[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
        mData2 = data2
        mDataImageString = dataImageString
    }
}