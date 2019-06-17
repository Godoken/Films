package com.example.films.features.films.data

import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

class DataSourceImpl : DataSource {
    override fun loadFilms(): Single<List<Film>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}