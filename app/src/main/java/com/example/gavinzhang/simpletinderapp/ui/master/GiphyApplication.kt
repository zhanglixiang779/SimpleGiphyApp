package com.example.gavinzhang.simpletinderapp.ui.master

import com.example.gavinzhang.simpletinderapp.ui.master.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class GiphyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out GiphyApplication> =
        DaggerAppComponent.builder().create(this)

}