package com.geekbrains.rickmortie.network

import com.geekbrains.rickmortie.model.Character
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RickAndMortieClient @Inject constructor(
    private val api: RickAndMortieApi,
) {

    fun getCharacterInfo(id: String): Single<List<Character>> =
        api.getCharacterById(id = id).map {
            it.results
        }

    fun getMultipleCharacters(ids: List<Int>) {
        val request: String = ids.joinToString { "," }
        api.getCharacterById(id = request)
    }
}