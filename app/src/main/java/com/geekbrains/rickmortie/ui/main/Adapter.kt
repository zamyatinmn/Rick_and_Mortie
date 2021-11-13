package com.geekbrains.rickmortie.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.rickmortie.R
import com.geekbrains.rickmortie.model.Character
import com.geekbrains.rickmortie.model.Location
import com.geekbrains.rickmortie.ui.IScreens
import com.github.terrakok.cicerone.Router
import com.squareup.picasso.Picasso
import javax.inject.Inject


class Adapter : RecyclerView.Adapter<BaseViewHolder>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    private val dataSet: MutableList<Character> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Character>) {
        val insertIndex = dataSet.size
        dataSet.addAll(insertIndex, data)
        if (insertIndex == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(insertIndex, data.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_card, parent, false)
        return BaseViewHolder(v)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentCharacter = dataSet[position]
        holder.itemView.setOnClickListener {
            router.replaceScreen(screens.characterDetails(currentCharacter))
        }
        holder.setData(currentCharacter)
    }

    override fun getItemCount() = dataSet.size
}

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(character: Character) {
        itemView.apply {
            val image = character.image
            if (image.isNotBlank()) {
                Picasso.get()
                    .load(image)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(findViewById<ImageView>(R.id.portrait))
            }

            findViewById<TextView>(R.id.name).text = character.name
        }
    }
}