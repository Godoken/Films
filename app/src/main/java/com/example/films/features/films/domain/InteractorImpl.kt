package com.example.films.features.films.domain

import com.example.films.features.films.domain.model.Film
import io.reactivex.Single

class InteractorImpl(private val repository: Repository) : Interactor {
    override fun loadFilms(): Single<List<Film>> {
        return repository.loadFilms()
            .map { list: List<Film> -> sortFilmsByYear(list)}
            //.map { list: List<Film> -> sortFilms(list)}
    }

    private fun sortFilmsByYear(list: List<Film>): List<Film> {
        val sortedList: MutableList<Film> = ArrayList<Film>()
        //val bookList: MutableList<Product> = ArrayList<Product>()
        //val diskList: MutableList<Product> = ArrayList<Product>()

        /*list.forEach { product: Product -> if (product.getProductType() == "Book") {
            bookList.add(product)
        } else {
            diskList.add(product)
        }}*/

        //sortedList.addAll(bookList)
        //sortedList.addAll(diskList)

        return sortedList
    }
}