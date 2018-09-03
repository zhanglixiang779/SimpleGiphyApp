package com.example.gavinzhang.simpletinderapp.ui.master.di

import com.example.gavinzhang.simpletinderapp.ui.master.ui.fragments.DetailFragment
import com.example.gavinzhang.simpletinderapp.ui.master.ui.fragments.MasterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {

    @ContributesAndroidInjector(modules = [MasterFragmentModule::class])
    abstract fun masterFragment(): MasterFragment

    @ContributesAndroidInjector(modules = [DetailFragmentModule::class])
    abstract fun detailFragment(): DetailFragment

}