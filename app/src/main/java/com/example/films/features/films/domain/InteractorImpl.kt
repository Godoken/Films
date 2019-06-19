package com.example.films.features.films.domain

import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

class InteractorImpl(private val repository: Repository) : Interactor {

    override fun loadFilms(): Single<List<Film>> {
        return repository.loadFilms()
            .map { list: List<Film> -> sortFilmsByRating(list)  }
            .map { list: List<Film> -> sortFilmsByYear(list) }
            .map { list: List<Film> -> formatFilmsByYear(list)}
    }

    override fun loadFilmInformation(filmId: Int): Single<Film> {
        return repository.loadFilmInformation(filmId)
    }

    private fun sortFilmsByYear(list: List<Film>): List<Film> {
        return list.sortedByDescending { film: Film ->  film.year}
    }

    private fun sortFilmsByRating(list: List<Film>): List<Film> {
        return list.sortedByDescending { film: Film ->  film.rating}
    }

    private fun formatFilmsByYear(list: List<Film>): List<Film> {
        val formedList: MutableList<Film> = ArrayList()
        var yearSeparator = 0

        list.forEach { film: Film -> if (film.year != yearSeparator) {
            formedList.add(Film(0, film.year.toString(), "", null, null, null, null ))
            formedList.add(film)
            yearSeparator = film.year!!
        } else {
            formedList.add(film)
        }}

        return formedList
    }
}