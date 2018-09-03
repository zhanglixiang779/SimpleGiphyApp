package com.example.gavinzhang.simpletinderapp.ui.master.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.gavinzhang.simpletinderapp.ui.master.data.models.GiphyResponse
import com.example.gavinzhang.simpletinderapp.ui.master.utils.API_KEY
import com.example.gavinzhang.simpletinderapp.ui.master.utils.LIMIT

import com.example.gavinzhang.simpletinderapp.ui.master.utils.NetworkStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GiphyRepository @Inject constructor(val apiService: ApiService) {

    val giphyResponseLiveData = MutableLiveData<GiphyResponse>()
    val itemsLiveData = Transformations.map(giphyResponseLiveData) { it.data }
    val networkStatusLiveData = MutableLiveData<NetworkStatus>()

    fun fetchPhotos() {
        val call = apiService.fetchPhotos(query = com.example.gavinzhang.simpletinderapp.ui.master.utils.QUERY,
                                                            apiKey = API_KEY,
                                                            limit = LIMIT)
        call.enqueue(object : Callback<GiphyResponse> {
            override fun onFailure(call: Call<GiphyResponse>?, t: Throwable?) {
                //error handling
                networkStatusLiveData.value = NetworkStatus.FAILED
            }

            override fun onResponse(call: Call<GiphyResponse>?, response: Response<GiphyResponse>?) {
                giphyResponseLiveData.value = response?.body()
                networkStatusLiveData.value = NetworkStatus.LOADED
            }
        })
    }
}