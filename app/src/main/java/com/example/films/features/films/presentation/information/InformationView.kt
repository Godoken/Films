package com.example.films.features.films.presentation.information

import com.example.films.features.films.domain.model.Film

interface InformationView {
    fun showProgress()
    fun hideProgress()
    fun showError(textError: String)
    fun loadInformation()
    fun initInformation(film: Film)
}