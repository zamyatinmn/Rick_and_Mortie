package com.geekbrains.rickmortie.di

import com.geekbrains.rickmortie.App
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}