package com.geekbrains.rickmortie

import android.app.Application
import com.geekbrains.rickmortie.di.AppComponent
import com.geekbrains.rickmortie.di.AppModule
import com.geekbrains.rickmortie.di.DaggerAppComponent


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}