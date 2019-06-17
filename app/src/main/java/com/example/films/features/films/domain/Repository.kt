package com.example.films.features.films.domain

import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

interface Repository {
    fun loadFilms(): Single<List<Film>>
}