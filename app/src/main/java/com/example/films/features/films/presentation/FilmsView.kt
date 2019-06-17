package com.example.films.features.films.presentation

import com.example.films.features.films.domain.model.Film

interface FilmsView {
    fun showProgress()
    fun hideProgress()
    fun showError(textError: String)
    fun loadFilms()
    fun setFilmsToAdapter(films: List<Film>)
    fun onBackPressed()
    fun openQuitDialog()
}