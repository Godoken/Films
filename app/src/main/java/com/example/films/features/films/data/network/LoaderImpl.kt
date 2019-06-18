package com.example.films.features.films.data.network

import com.example.films.app.App
import com.example.films.features.films.data.Loader
import com.example.films.features.films.data.network.client.Api
import com.example.films.features.films.domain.model.Film
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LoaderImpl(private val api: Api) : Loader {
    override fun loadFilms(): Single<List<Film>> {
        return api.getFilmList()
            .subscribeOn(Schedulers.io())
            .map { listFilm: ListFilm -> listFilm.films}
            .doOnSuccess { films: List<Film> -> App.getDataBase().databaseDao.insertAllFilms(films) }
    }
}