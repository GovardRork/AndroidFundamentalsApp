package com.example.movieapp.presentation.movieList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import com.example.movieapp.common.setRatingStars
import com.example.movieapp.common.toString
import com.example.movieapp.model.Movie

class MovieListViewHolder(itemView: View) : ViewHolder(itemView) {
    private val movieImage: ImageView = itemView.findViewById(R.id.iv_movie_image)
    private val movieGenre: TextView = itemView.findViewById(R.id.tv_movie_genre)
    private val review: TextView = itemView.findViewById(R.id.tv_review)
    private val movieName: TextView = itemView.findViewById(R.id.tv_movie_name)
    private val movieDuration: TextView = itemView.findViewById(R.id.tv_movie_duration)
    private val pg: TextView = itemView.findViewById(R.id.tv_movie_pg)
    companion object{
        const val TAG = "MovieListViewHolder"
        private val EMPTY_MOVIE_DETAILS = Movie(0,0,null,null,0,0,false,0,null,null,null,null)
    }

    fun bindMovie(movie: Movie?) {
        if(movie != null)
            setMovieListItem(movie)
        else
            setMovieListItem(EMPTY_MOVIE_DETAILS)
    }

    private fun setMovieListItem(movie: Movie){
        val rating = movie.rating
        val requestOptions = RequestOptions()
            .override(166, 246)
            .transform(CenterCrop(), RoundedCorners(8))
        Glide.with(itemView)
            .load(movie.detailImageUrl)
            .apply(requestOptions)
//            .placeholder(R.drawable.loading_spinner)
            .into(movieImage)
        setRatingStars(rating,itemView)
        movieGenre.text = movie.genres.toString()
        review.text = itemView.resources.getString(R.string.tv_review, movie.reviewCount)
        movieName.text = movie.title
        movieDuration.text =
            itemView.resources.getString(R.string.tv_movie_time, movie.runningTime)
        pg.text = itemView.resources.getString(R.string.tv_plus13, movie.pgAge)
    }
}