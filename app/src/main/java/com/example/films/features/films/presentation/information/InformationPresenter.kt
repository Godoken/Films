package com.example.films.features.films.presentation.information

import com.example.films.R
import com.example.films.app.App
import com.example.films.features.films.domain.Interactor
import com.example.films.features.films.domain.model.Film
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class InformationPresenter(private val interactor: Interactor, private val filmId: Int) {

    protected var view: InformationView? = null

    fun attachView(view: InformationView) {
        this.view = view
        onViewReady()
    }

    fun detachView() {
        this.view = null
    }

    protected fun onViewReady() {
        view!!.loadInformation()
    }

    fun loadInformation() {
        val listObservable = interactor.loadFilmInformation(filmId).toObservable()
        val listObserver = object : Observer<Film> {
            override fun onSubscribe(d: Disposable) {
                view!!.showProgress()
            }

            override fun onNext(film: Film) {
                view!!.initInformation(film)
            }

            override fun onError(e: Throwable) {
                view!!.hideProgress()
                view!!.showError(App.getContext().getString(R.string.error_film_list_server))
            }

            override fun onComplete() {
                view!!.hideProgress()
            }
        }
        listObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(listObserver)
    }
}