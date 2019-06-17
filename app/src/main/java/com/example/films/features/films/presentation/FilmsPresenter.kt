package com.example.films.features.films.presentation

import com.example.films.features.films.domain.Interactor
import com.example.films.features.films.domain.model.Film
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class FilmsPresenter(private val interactor: Interactor) {

    protected var view: FilmsView? = null

    fun attachView(view: FilmsView) {
        this.view = view
        onViewReady()
    }

    fun detachView() {
        this.view = null
    }

    protected fun onViewReady() {
        view!!.loadFilms()
    }

    fun loadFilms() {
        val listObservable = interactor.loadFilms().toObservable()
        val listObserver = object : Observer<List<Film>> {
            override fun onSubscribe(d: Disposable) {
                view!!.showProgress()
            }

            override fun onNext(films: List<Film>) {
                if (films.size != 0) {
                    view!!.setFilmsToAdapter(films)
                } else {
                    //view!!.showError(App.getContext().getString(R.string.error_event_list_clear))
                }
            }

            override fun onError(e: Throwable) {
                view!!.hideProgress()
                //view!!.showError(App.getContext().getString(R.string.error_events_list_server))
            }

            override fun onComplete() {
                view!!.hideProgress()
            }
        }
        listObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(listObserver)
    }

    fun onFilmSelected(films: Film) {
        view!!.showProgress()
        //view!!.loadInformation(productsInteractor.getInformation(product))
        view!!.hideProgress()

    }

    fun onBackPressed() {
        view!!.showProgress()
        view!!.openQuitDialog()
        view!!.hideProgress()
    }
}