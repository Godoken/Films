package com.example.films.features.films.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

@Dao
interface FilmDao {

    @Query("SELECT * FROM film")
    fun getAllFilms(): Single<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFilms(filmList: List<Film>)

    @Query("SELECT * FROM film WHERE id = :filmId")
    fun getAllInformationByFilmId(filmId: Int): Single<Film>
}