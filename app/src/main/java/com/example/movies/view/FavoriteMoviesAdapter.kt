package com.example.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.dtos.MovieDTO
import com.example.movies.view.FavoriteMoviesAdapter.*

class FavoriteMoviesAdapter (
    private val favoriteMovies: List<MovieDTO>
): RecyclerView.Adapter<FavoriteMovieViewHolder>() {

    private val imageURL = "http://image.tmdb.org/t/p/w185"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.favorite_movie_card, parent, false
        )

        return FavoriteMovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movie = favoriteMovies[position]
        Glide.with(holder.poster.context).load(imageURL + movie.posterPath).into(holder.poster)
        holder.title.text = movie.title
        holder.description.text = movie.overview

    }

    override fun getItemCount(): Int {
        return favoriteMovies.size
    }

    class FavoriteMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.favorite_poster)
        val title: TextView = itemView.findViewById(R.id.favorite_title)
        val description: TextView = itemView.findViewById(R.id.favorite_description)
    }
}