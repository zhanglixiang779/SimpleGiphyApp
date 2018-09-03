package com.example.gavinzhang.simpletinderapp.ui.master.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gavinzhang.simpletinderapp.R
import com.example.gavinzhang.simpletinderapp.ui.master.adapters.GiphyAdapter
import com.example.gavinzhang.simpletinderapp.ui.master.utils.NetworkStatus
import com.example.gavinzhang.simpletinderapp.ui.master.utils.QUERY
import com.example.gavinzhang.simpletinderapp.ui.master.viewmodels.SharedViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MasterFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: SharedViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var search: EditText
    private val recyclerViewAdapter by lazy {
        GiphyAdapter(::callback)
    }

    companion object {
        const val COLUMN_SIZE = 3
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.master_fragment, container, false)
        view.run {
            recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
                adapter = recyclerViewAdapter
                layoutManager = GridLayoutManager(context, COLUMN_SIZE)
                setHasFixedSize(true)
            }

            search = findViewById<EditText>(R.id.search).apply {
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        viewModel.queryLiveData.value = p0.toString()
                    }
                })
            }

            swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchPhotos()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        observeItems()
        observeNetworkStatus()
        observeQuery()
    }

    internal fun getViewModel(): SharedViewModel {
        return ViewModelProviders.of(activity!!, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return viewModel as T
            }

        }).get(SharedViewModel::class.java)
    }

    private fun callback(url: String, source: String, view: View) {
        val action = MasterFragmentDirections.actionMasterFragmentToDetailFragment()
        action.run {
            setUrl(url)
            setSource(source)
        }

        Navigation.findNavController(view).navigate(action)
    }

    private fun observeItems() {
        viewModel.itemsLiveData.observe(this, Observer {
            recyclerViewAdapter.submitList(it)
        })
    }

    private fun observeNetworkStatus() {
        viewModel.networkStatusLiveData.observe(this, Observer {
            when (it!!) {
                NetworkStatus.LOADING -> swipeRefreshLayout.isRefreshing = true
                NetworkStatus.LOADED -> swipeRefreshLayout.isRefreshing = false
                NetworkStatus.FAILED -> swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun observeQuery() {
        viewModel.queryLiveData.observe(this, Observer {
            QUERY = it
            viewModel.fetchPhotos()
        })
    }
}
