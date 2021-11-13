package com.geekbrains.rickmortie.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekbrains.rickmortie.model.Character
import io.reactivex.rxjava3.core.Single


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterList(characters: List<Character>)

    @Query("SELECT * FROM Character WHERE id > :min AND id <= :max")
    fun getCharacterList(min: Int, max: Int): Single<List<Character>>
}
