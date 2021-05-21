package com.example.lab_1_v1

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*


class PhotosAdapter(private val context: Context, private val photos: List<OnePhoto>)
    : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private val TAG = "PhotosAdapter"

    // Usually involves inflating a layout from XML and returning the holder - THIS IS EXPENSIVE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    // Returns the total count of items in the list
    override fun getItemCount() = photos.size

    // Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val contact = photos[position]
        holder.bind(contact)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: OnePhoto) {
            val uriPhoto = Uri.parse(photo.photoPath)
            itemView.ivProfile.setImageURI(uriPhoto)
        }
    }
}