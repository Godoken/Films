package com.example.films.features.films.presentation.information

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.films.R
import com.example.films.features.films.domain.model.Film
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity(), InformationView {

    private var informationPresenter: InformationPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        informationPresenter = PresenterFactory.createPresenter(intent.getIntExtra("id", 0))
    }

    override fun onStart() {
        super.onStart()
        informationPresenter!!.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        informationPresenter!!.detachView()
    }

    override fun showProgress() {
        film_information_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        film_information_progress.visibility = View.GONE
    }

    override fun showError(textError: String) {
        val toast: Toast = Toast.makeText(this, textError, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun loadInformation() {
        informationPresenter!!.loadInformation()
    }

    override fun initInformation(film: Film) {
        film_localized_name.text = film.localized_name
        film_name.text = film.name
        film_year.text = "Год: ${film.year}"
        film.rating?.let { film_rating.text  = "Рейтинг: ${film.rating}" }
        film_description.text = film.description

        Picasso.get().load(film.image_url)
            .placeholder(R.mipmap.ic_launcher)
            .into(imageView)
    }
}