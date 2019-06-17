package com.example.films.features.films.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.films.R
import com.example.films.features.films.domain.model.Film

import java.util.ArrayList

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

        init {
            filmNameView = itemView.findViewById(R.id.film_item_name)
            filmTextView = itemView.findViewById(R.id.film_item_text)
        }

        fun bind(film: Film) {
            filmNameView.text = film.translatedTitle
            filmTextView.text = film.description

            itemView.setOnClickListener { v -> selectFilmListener.onFilmSelect(film) }
        }
    }

    interface SelectFilmListener {
        fun onFilmSelect(film: Film)
    }
}
