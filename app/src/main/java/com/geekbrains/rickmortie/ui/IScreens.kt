package com.geekbrains.rickmortie.ui

import com.geekbrains.rickmortie.model.Character
import com.github.terrakok.cicerone.Screen


interface IScreens {
    fun characterList(): Screen
    fun characterDetails(character: Character): Screen
}