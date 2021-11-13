package com.geekbrains.rickmortie.ui

import com.geekbrains.rickmortie.model.Character
import com.geekbrains.rickmortie.ui.detail.DetailFragment
import com.geekbrains.rickmortie.ui.main.MainFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject


class AndroidScreens @Inject constructor() : IScreens {
    override fun characterList(): Screen {
        return FragmentScreen { MainFragment.newInstance() }
    }

    override fun characterDetails(character: Character): Screen {
        return FragmentScreen { DetailFragment.newInstance(character) }
    }
}