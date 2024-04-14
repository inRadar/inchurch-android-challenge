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
import com.example.movies.model.dtos.MoviesDTO
import com.example.movies.view.MoviesAdapter.MovieViewHolder

class MoviesAdapter(
    private val moviesDto: MoviesDTO,
    private val activity: MainActivity
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

        holder.starIcon.setImageResource(
            if(activity.isMovieFavorite(movie.id.toString())) {
                R.drawable.star_filled
            } else {
                R.drawable.star
            }
        )

        holder.starIcon.setOnClickListener {
            activity.upDateFavoriteList(movie.id.toString())
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return moviesDto.movies.size
    }


    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val moviePoster: ImageView = itemView.findViewById(R.id.poster)
        val movieName: TextView = itemView.findViewById(R.id.movie_name)
        val starIcon: ImageView = itemView.findViewById(R.id.star_icon)
    }

}

