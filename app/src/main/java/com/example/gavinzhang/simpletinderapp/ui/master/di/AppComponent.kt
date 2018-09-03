package com.example.gavinzhang.simpletinderapp.ui.master.di

import com.example.gavinzhang.simpletinderapp.ui.master.GiphyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    ActivitiesBindingModule::class])
interface AppComponent : AndroidInjector<GiphyApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<GiphyApplication>()
}