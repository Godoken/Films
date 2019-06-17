package com.example.films.features.films.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.films.R
import com.example.films.features.films.domain.model.Film
import kotlinx.android.synthetic.main.activity_main.*

class FilmsActivity : AppCompatActivity(), FilmsView {

    private var filmsPresenter: FilmsPresenter? = null
    private var filmsAdapter: FilmsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        filmsPresenter = PresenterFactory.createPresenter()

        initView()
    }

    private fun initView() {
        filmsAdapter = FilmsAdapter(this@FilmsActivity, object : FilmsAdapter.SelectFilmListener {
            override fun onFilmSelect(film: Film) {
                filmsPresenter!!.onFilmSelected(film)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        filmsPresenter!!.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        filmsPresenter!!.detachView()
    }

    override fun showProgress() {
        films_progress_bar.visibility = View.VISIBLE
        films_recycler_view.visibility = View.GONE
    }

    override fun hideProgress() {
        films_progress_bar.visibility = View.GONE
        films_recycler_view.visibility = View.VISIBLE
    }

    override fun showError(textError: String) {
        val toast: Toast = Toast.makeText(this, textError, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onBackPressed() {
        filmsPresenter!!.onBackPressed()
    }

    override fun openQuitDialog() {
        val quitDialog = AlertDialog.Builder(this)
        quitDialog.setTitle(R.string.on_back_pressed)
        quitDialog.setPositiveButton(R.string.yes) { dialog, which -> finish() }
        quitDialog.setNegativeButton(R.string.no) { dialog, which -> }
        quitDialog.show()
    }

    override fun loadFilms() {
        filmsPresenter!!.loadFilms()
    }

    override fun setFilmsToAdapter(films: List<Film>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
