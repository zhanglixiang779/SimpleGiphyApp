package com.example.gavinzhang.simpletinderapp.ui.master.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.gavinzhang.simpletinderapp.ui.master.data.models.Item

class GiphyDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem
}