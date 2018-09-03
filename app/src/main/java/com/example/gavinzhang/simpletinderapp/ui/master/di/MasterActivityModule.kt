package com.example.gavinzhang.simpletinderapp.ui.master.di

import com.example.gavinzhang.simpletinderapp.ui.master.data.GiphyRepository
import com.example.gavinzhang.simpletinderapp.ui.master.viewmodels.SharedViewModel
import dagger.Module
import dagger.Provides

@Module
class MasterActivityModule {
    @Provides
    fun provideSharedViewModel(repository: GiphyRepository) =
            SharedViewModel(repository)
}