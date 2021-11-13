package com.geekbrains.rickmortie.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.rickmortie.App
import com.geekbrains.rickmortie.R
import com.geekbrains.rickmortie.databinding.ActivityMainBinding
import com.geekbrains.rickmortie.ui.BackButtonListener
import com.geekbrains.rickmortie.ui.IScreens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screns: IScreens

    private val navigator = AppNavigator(this, R.id.container)

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        router.replaceScreen(screns.characterList())
        setSupportActionBar(binding.toolbar)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        router.exit()
    }
}