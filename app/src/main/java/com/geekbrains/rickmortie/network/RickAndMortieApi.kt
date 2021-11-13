package com.geekbrains.rickmortie.network

import com.geekbrains.rickmortie.model.Result
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface RickAndMortieApi {

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: String): Single<Result>

}
