package com.geekbrains.rickmortie.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.geekbrains.rickmortie.App
import com.geekbrains.rickmortie.R
import com.geekbrains.rickmortie.databinding.FragmentDetailBinding
import com.geekbrains.rickmortie.model.Character
import com.geekbrains.rickmortie.ui.BackButtonListener
import com.geekbrains.rickmortie.ui.IScreens
import com.geekbrains.rickmortie.ui.ViewBindingFragment
import com.github.terrakok.cicerone.Router
import com.squareup.picasso.Picasso
import javax.inject.Inject


class DetailFragment : ViewBindingFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate),
    BackButtonListener {
    companion object {
        private const val CHARACTER_KEY = "character_key"
        fun newInstance(character: Character): Fragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CHARACTER_KEY, character)
                }
                App.instance.appComponent.inject(this)
            }
        }
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            bundle.getParcelable<Character>(CHARACTER_KEY)?.apply {
                if (image.isNotBlank()){
                    Picasso.get()
                        .load(image)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(binding.portrait)
                }

            }
        }
    }

    override fun backPressed(): Boolean {
        router.replaceScreen(screens.characterList())
        return true
    }
}