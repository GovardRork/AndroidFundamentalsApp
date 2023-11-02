package com.example.movieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.FragmentMovieDetails.Companion.setRatingStars
import com.example.movieapp.model.Movie

class MovieListViewHolder(itemView: View) : ViewHolder(itemView) {
    private val movieImage: ImageView = itemView.findViewById(R.id.iv_movie_image)
    private val movieGenre: TextView = itemView.findViewById(R.id.tv_movie_genre)
    private val review: TextView = itemView.findViewById(R.id.tv_review)
    private val movieName: TextView = itemView.findViewById(R.id.tv_movie_name)
    private val movieDuration: TextView = itemView.findViewById(R.id.tv_movie_duration)
    private val pg: TextView = itemView.findViewById(R.id.tv_movie_pg)

    fun bindMovieDetails(movieDetails: Movie) {
        val rating = movieDetails.rating
        val requestOptions = RequestOptions()
            .override(166, 246)
            .transform(CenterCrop(), RoundedCorners(8))
        Glide.with(itemView)
            .load(movieDetails.detailImageUrl)
            .apply(requestOptions)
//            .placeholder(R.drawable.loading_spinner)
            .into(movieImage)
        setRatingStars(rating,itemView)
        movieGenre.text = movieDetails.genres.toString()
        review.text = itemView.resources.getString(R.string.tv_review, movieDetails.reviewCount)
        movieName.text = movieDetails.title
        movieDuration.text =
            itemView.resources.getString(R.string.tv_movie_time, movieDetails.runningTime)
        pg.text = itemView.resources.getString(R.string.tv_plus13, movieDetails.pgAge)
    }
}