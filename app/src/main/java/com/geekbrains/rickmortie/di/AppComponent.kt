package com.geekbrains.rickmortie.di

import com.geekbrains.rickmortie.ui.detail.DetailFragment
import com.geekbrains.rickmortie.ui.main.Adapter
import com.geekbrains.rickmortie.ui.main.MainActivity
import com.geekbrains.rickmortie.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    PersistenceModule::class,
    CiceroneModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainViewModel)
    fun inject(adapter: Adapter)
    fun inject(detailFragment: DetailFragment)
}