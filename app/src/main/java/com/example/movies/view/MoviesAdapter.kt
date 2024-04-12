package com.example.movies.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.dtos.MovieDTO
import com.example.movies.model.dtos.MoviesDTO
import com.example.movies.view.MoviesAdapter.MovieViewHolder

class MoviesAdapter(
    private val moviesDto: MoviesDTO,
    private val context: Context
): RecyclerView.Adapter<MovieViewHolder>() {

    private val imageURL = "http://image.tmdb.org/t/p/w185"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_card, parent, false
        )

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: MovieDTO = moviesDto.movies[position]
        Glide.with(holder.moviePoster.context).load(imageURL + movie.posterPath).into(holder.moviePoster)
        holder.movieName.text = movie.title
    }

    override fun getItemCount(): Int {
        return moviesDto.movies.size
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val view = itemView
        val moviePoster: ImageView = itemView.findViewById(R.id.poster)
        val movieName: TextView = itemView.findViewById(R.id.movie_name)
        val starIcon: ImageView = itemView.findViewById(R.id.star_icon)
    }

}

