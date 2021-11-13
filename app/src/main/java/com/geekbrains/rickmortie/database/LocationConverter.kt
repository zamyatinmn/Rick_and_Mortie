package com.geekbrains.rickmortie.database

import androidx.room.TypeConverter
import com.geekbrains.rickmortie.model.Location

class LocationConverter {
    companion object {
        @JvmStatic
        @TypeConverter
        fun toLocation(locationString: String): Location {
            val data = locationString.split(";")
            return Location(data[0], data[1])
        }

        @JvmStatic
        @TypeConverter
        fun fromLocation(location: Location): String {
            return "${location.name};${location.url}"
        }
    }
}
