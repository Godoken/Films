package com.example.films.features.films.data.db

import com.example.films.app.App
import com.example.films.features.films.data.DataSource
import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

class DataSourceImpl : DataSource {
    override fun loadFilms(): Single<List<Film>> {
        return App.getDataBase().databaseDao.getAllFilms()
    }
}