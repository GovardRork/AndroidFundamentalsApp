package com.example.movieapp.domain

import com.example.movieapp.common.Result
import com.example.movieapp.model.Movie

internal interface MovieRepository {

    suspend fun loadMovies(): Result<List<Movie>>
    suspend fun loadMovie(movieId: Int): Result<Movie>
}