package com.example.gavinzhang.simpletinderapp.ui.master.ui.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.gavinzhang.simpletinderapp.R
import com.example.gavinzhang.simpletinderapp.ui.master.adapters.GiphyAdapter
import com.example.gavinzhang.simpletinderapp.ui.master.data.models.Item
import com.squareup.picasso.Picasso

class GiphyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var photo: ImageView

    fun onbind(item: Item, callback: (String, String, View) -> Unit ) {
        val url = item.images.original_still.url
        val source = item.source_tld
        itemView.setOnClickListener {
            callback(url, source, itemView)
        }
        itemView.run {
            photo = findViewById(R.id.photo)
            loadPhoto(url)
        }
    }

    private fun loadPhoto(url: String) {
        Picasso.get()
                .load(url)
                .resize(GiphyAdapter.IMAGE_SIZE, GiphyAdapter.IMAGE_SIZE)
                .centerCrop()
                .into(photo)
    }
}