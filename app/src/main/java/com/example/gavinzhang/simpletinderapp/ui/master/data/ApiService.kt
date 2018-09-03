package com.example.gavinzhang.simpletinderapp.ui.master.data

import com.example.gavinzhang.simpletinderapp.ui.master.data.models.GiphyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/gifs/search")
    fun fetchPhotos(@Query("q") query: String,
                      @Query("api_key") apiKey: String,
                      @Query("limit") limit: Int):
            Call<GiphyResponse>
}