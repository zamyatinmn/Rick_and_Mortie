package com.geekbrains.rickmortie.di

import com.geekbrains.rickmortie.network.RickAndMortieApi
import com.geekbrains.rickmortie.network.RickAndMortieClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://rickandmortyapi.com/api/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortieApi(retrofit: Retrofit): RickAndMortieApi {
        return retrofit.create(RickAndMortieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRickAndMortieClient(api: RickAndMortieApi): RickAndMortieClient {
        return RickAndMortieClient(api)
    }
}