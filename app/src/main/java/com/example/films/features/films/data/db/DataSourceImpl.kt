package com.example.films.features.films.data.db

import com.example.films.app.App
import com.example.films.features.films.data.DataSource
import com.example.films.features.films.domain.model.Film
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DataSourceImpl : DataSource {
    override fun loadFilmInformation(filmId: Int): Single<Film> {
        return App.getDataBase().databaseDao.getAllInformationByFilmId(filmId)
            .subscribeOn(Schedulers.io())
    }

    override fun loadFilms(): Single<List<Film>> {
        return App.getDataBase().databaseDao.getAllFilms()
            .subscribeOn(Schedulers.io())
    }
}