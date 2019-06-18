package com.example.films.features.films.data

import com.example.films.features.films.domain.Repository
import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

class RepositoryImpl(private val dataSource: DataSource, private val loader: Loader) : Repository {
    override fun loadFilms(): Single<List<Film>> {
        return loadFilmsFromNet()
            .onErrorResumeNext {loadFilmsFromDatabase() }
    }

    private fun loadFilmsFromNet(): Single<List<Film>> {
        return loader.loadFilms()
    }

    private fun loadFilmsFromDatabase(): Single<List<Film>> {
        return dataSource.loadFilms()
    }
}