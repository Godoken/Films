package com.example.films.features.films.data.network.client

import com.example.films.features.films.data.network.ListFilm
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("sequeniatesttask/films.json")
    fun getFilmList(): Single<ListFilm>
}