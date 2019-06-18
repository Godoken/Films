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

    private fun sortFilmsByYear(list: List<Film>): List<Film> {
        return list.sortedByDescending { film: Film ->  film.year}
    }

    private fun sortFilmsByRating(list: List<Film>): List<Film> {
        return list.sortedByDescending { film: Film ->  film.rating}
    }

    private fun formatFilmsByYear(list: List<Film>): List<Film> {
        val formatedList: MutableList<Film> = ArrayList<Film>()
        var yearSeparator: Int = 0

        list.forEach { film: Film -> if (film.year != yearSeparator) {
            formatedList.add(Film(0, film.year.toString(), "", null, null, null, null ))
            formatedList.add(film)
            yearSeparator = film.year!!
        } else {
            formatedList.add(film)
        }}

        return formatedList
    }
}