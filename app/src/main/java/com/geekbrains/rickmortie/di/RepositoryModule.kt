package com.geekbrains.rickmortie.di

import com.geekbrains.rickmortie.MainRepository
import com.geekbrains.rickmortie.database.CharacterDao
import com.geekbrains.rickmortie.network.RickAndMortieClient
import dagger.Module
import dagger.Provides


@Module
object RepositoryModule {

    @Provides
    fun provideMainRepository(
        client: RickAndMortieClient,
        dao: CharacterDao,
    ): MainRepository {
        return MainRepository(client, dao)
    }
}