package com.example.gavinzhang.simpletinderapp.ui.master.di

import android.app.Application
import android.content.Context
import com.example.gavinzhang.simpletinderapp.ui.master.data.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    companion object {
        const val BASE_URL = "http://api.giphy.com/"
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)

}