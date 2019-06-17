package com.example.films.features.films.data

import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

interface Loader {
    fun loadFilms(): Single<List<Film>>
}