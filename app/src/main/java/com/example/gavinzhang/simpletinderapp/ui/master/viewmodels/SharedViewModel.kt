package com.example.gavinzhang.simpletinderapp.ui.master.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gavinzhang.simpletinderapp.ui.master.data.GiphyRepository
import com.example.gavinzhang.simpletinderapp.ui.master.utils.NetworkStatus

class SharedViewModel (private val repository: GiphyRepository) : ViewModel() {

    var itemsLiveData = repository.itemsLiveData
    var networkStatusLiveData = repository.networkStatusLiveData
    val queryLiveData = MutableLiveData<String>()

    fun fetchPhotos() {
        networkStatusLiveData.value = NetworkStatus.LOADING
        repository.fetchPhotos()
    }
}
