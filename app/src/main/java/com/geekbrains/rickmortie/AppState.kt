package com.geekbrains.rickmortie

import com.geekbrains.rickmortie.model.Character

sealed class AppState {
    object Loading : AppState()
    data class Success(val result: List<Character>) : AppState()
}
