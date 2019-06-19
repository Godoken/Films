package com.example.films.features.films.presentation.films

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.films.R
import com.example.films.app.App
import com.example.films.features.films.domain.model.Film
import java.util.*

class FilmsAdapter(context: Context, private val selectFilmListener: SelectFilmListener) :
    RecyclerView.Adapter<FilmsAdapter.FilmsHolder>() {

    private val films = ArrayList<Film>()
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsHolder {
        val itemView = layoutInflater.inflate(R.layout.film_item, parent, false)
        return FilmsHolder(itemView, selectFilmListener)
    }

    override fun onBindViewHolder(holder: FilmsHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int {
        return films.size
    }

    fun setFilms(filmList: List<Film>) {
        films.clear()
        films.addAll(filmList)
        notifyDataSetChanged()
    }

    inner class FilmsHolder(itemView: View, private val selectFilmListener: SelectFilmListener) :
        RecyclerView.ViewHolder(itemView) {

        private val filmNameView: TextView
        private val filmTextView: TextView
        private val filmRatingView: TextView

        init {
            filmNameView = itemView.findViewById(R.id.film_item_name)
            filmTextView = itemView.findViewById(R.id.film_item_text)
            filmRatingView = itemView.findViewById(R.id.film_item_rating)
        }

        fun bind(film: Film) {
            filmNameView.text = film.localized_name
            filmTextView.text = film.name

            if (film.id != 0){
                film.rating?.let { filmRatingView.text = film.rating.toString() }
                itemView.background = App.getContext().getDrawable(R.drawable.background_on_click)
                itemView.isClickable = true
                itemView.setOnClickListener { v -> selectFilmListener.onFilmSelect(film) }
            } else {
                itemView.setBackgroundColor(Color.LTGRAY)
                itemView.isClickable = false
                filmRatingView.text = ""
            }
        }
    }

    interface SelectFilmListener {
        fun onFilmSelect(film: Film)
    }
}
