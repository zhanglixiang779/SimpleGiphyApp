package com.example.gavinzhang.simpletinderapp.ui.master.di

import com.example.gavinzhang.simpletinderapp.ui.master.ui.activities.MasterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector(modules = [MasterActivityModule::class,
        FragmentsBindingModule::class])
    abstract fun masterActivity(): MasterActivity

}