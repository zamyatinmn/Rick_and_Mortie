package com.geekbrains.rickmortie

import android.util.Log
import com.geekbrains.rickmortie.database.CharacterDao
import com.geekbrains.rickmortie.model.Character
import com.geekbrains.rickmortie.network.RickAndMortieClient
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val client: RickAndMortieClient,
    private val dao: CharacterDao,
) {
    companion object {
        private const val TAG = "MainRepository"
    }

    // TODO: 13.11.2021 Сделать пагинацию
    private val page = 1
    private val count = 20
    private val min = count * page - count
    private val max = count * page

    fun fetchCharacterList(page: Int): Observable<List<Character>> =
        client.getCharacterInfo("").toObservable()
            .doOnNext {
                dao.insertCharacterList(it)
            }
            .doOnError {
                Log.d(TAG, it.toString())
            }
            .onErrorResumeNext {
                dao.getCharacterList(min, max).toObservable()
            }
}