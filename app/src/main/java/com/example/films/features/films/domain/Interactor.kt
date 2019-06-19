package com.example.films.features.films.domain

import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

interface Interactor {
    fun loadFilms(): Single<List<Film>>
    fun loadFilmInformation(filmId: Int): Single<Film>
}