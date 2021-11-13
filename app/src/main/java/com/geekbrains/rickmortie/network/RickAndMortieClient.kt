package com.geekbrains.rickmortie.network

import com.geekbrains.rickmortie.model.Character
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RickAndMortieClient @Inject constructor(
    private val api: RickAndMortieApi,
) {
    fun getMultipleCharacters(ids: List<Int>): Single<List<Character>> {
        val request: String = ids.joinToString {
            "$it" }
        return api.getCharacterById(id = request)
    }
}