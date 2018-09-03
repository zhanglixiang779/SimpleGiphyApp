package com.example.gavinzhang.simpletinderapp.ui.master.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.gavinzhang.simpletinderapp.R
import com.example.gavinzhang.simpletinderapp.ui.master.data.models.Item
import com.example.gavinzhang.simpletinderapp.ui.master.ui.viewholders.GiphyViewHolder
import com.example.gavinzhang.simpletinderapp.ui.master.utils.GiphyDiffCallback

class GiphyAdapter(private val callback: (String, String, View) -> Unit)
    : ListAdapter<Item, GiphyViewHolder>(GiphyDiffCallback()) {

    companion object {
        const val IMAGE_SIZE = 500
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_view, parent, false)
        return GiphyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.onbind(getItem(position), callback)
    }
}