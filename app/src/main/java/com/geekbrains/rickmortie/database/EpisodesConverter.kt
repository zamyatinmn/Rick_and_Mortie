package com.geekbrains.rickmortie.database

import androidx.room.TypeConverter


class EpisodesConverter {
    companion object {
        @JvmStatic
        @TypeConverter
        fun toEpisodes(episodes: String): List<String> {
            val data = episodes.split(",")
            val converted = mutableListOf<String>()
            data.forEach {
                converted.add(it)
            }
            return converted
        }

        @JvmStatic
        @TypeConverter
        fun fromEpisodes(episodes: List<String>): String {
            return episodes.joinToString { it }
        }
    }
}