package com.geekbrains.rickmortie.ui.main

import android.util.Log
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.geekbrains.rickmortie.AppState
import com.geekbrains.rickmortie.MainRepository
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject


class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    @Inject
    lateinit var repository: MainRepository

    private val subjectLoading = BehaviorSubject.create<Unit>()
    private val subjectRequest = BehaviorSubject.create<Unit>()

    private var nextPage = 1

    private val observableResponse = subjectRequest
        .doOnNext { subjectLoading.onNext(Unit) }
        .flatMap {
            repository.fetchCharacterList(nextPage).map {
                AppState.Success(it)
            }
        }

    private val screenState = Observable.merge(listOf(
        subjectLoading.map { AppState.Loading },
        observableResponse.doOnError { Log.d(TAG, it.toString()) }
    ))

    private val liveDataObserver =
        LiveDataReactiveStreams.fromPublisher(screenState.toFlowable(BackpressureStrategy.LATEST))

    fun getLiveData() = liveDataObserver

    fun getData() {
        subjectRequest.onNext(Unit)
    }
}