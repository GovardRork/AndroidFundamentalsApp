package com.example.movieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieViewHolder(itemView: View): ViewHolder(itemView) {
    private val movieImage:ImageView = itemView.findViewById(R.id.iv_movie_image)
    private val movieGenre:TextView = itemView.findViewById(R.id.tv_movie_genre)
    private val ratingStar1:ImageView = itemView.findViewById(R.id.iv_rating_star_1)
    private val ratingStar2:ImageView = itemView.findViewById(R.id.iv_rating_star_2)
    private val ratingStar3:ImageView = itemView.findViewById(R.id.iv_rating_star_3)
    private val ratingStar4:ImageView = itemView.findViewById(R.id.iv_rating_star_4)
    private val ratingStar5:ImageView = itemView.findViewById(R.id.iv_rating_star_5)
    private val review:TextView = itemView.findViewById(R.id.tv_review)
    private val movieName:TextView = itemView.findViewById(R.id.tv_movie_name)
    private val movieDuration:TextView = itemView.findViewById(R.id.tv_movie_duration)
    private val pg:TextView = itemView.findViewById(R.id.tv_movie_pg)

    fun bindMovie(movie:Movie){
        val rating = movie.rating
        val requestOptions = RequestOptions()
            .override(166,246)
            .transform(CenterCrop(),RoundedCorners(8))
        Glide.with(itemView)
            .load(movie.image)
            .apply(requestOptions)
//            .placeholder(R.drawable.loading_spinner)
            .into(movieImage)
        setRatingStars(rating)
        movieGenre.text = movie.genre
        review.text = itemView.resources.getString(R.string.tv_review, movie.reviews)
        movieName.text = movie.name
        movieDuration.text = itemView.resources.getString(R.string.tv_movie_time,movie.durationInMin)
        pg.text = itemView.resources.getString(R.string.tv_plus13,movie.pg)
    }

    //TODO implement the method below
    private fun setRatingStars(rating:Double){
        ratingStar1.setStarToImageView(R.drawable.ic_star_red)
        ratingStar2.setStarToImageView(R.drawable.ic_star_red)
        ratingStar3.setStarToImageView(R.drawable.ic_star_red)
        ratingStar4.setStarToImageView(R.drawable.ic_star_red)
        ratingStar5.setStarToImageView(R.drawable.ic_star_red)
    }

    private fun ImageView.setStarToImageView(id:Int){
        this.setImageDrawable(ResourcesCompat.getDrawable(resources,id,null))
    }

}