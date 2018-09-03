package com.example.gavinzhang.simpletinderapp.ui.master.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.gavinzhang.simpletinderapp.R
import com.example.gavinzhang.simpletinderapp.ui.master.viewmodels.SharedViewModel
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment

class DetailFragment : DaggerFragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.detail_fragment, container, false)
        val picture = view.findViewById<ImageView>(R.id.picture)
        val source = view.findViewById<TextView>(R.id.source)
        val url = DetailFragmentArgs.fromBundle(arguments).url
        val sourceText = DetailFragmentArgs.fromBundle(arguments).source
        source.text = if (sourceText != "") {
            sourceText
        } else {
            resources.getString(R.string.not_available)
        }

        Picasso.get().load(url).into(picture)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
    }
}
