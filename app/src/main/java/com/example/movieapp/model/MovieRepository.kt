package com.example.movieapp.model

import com.example.movieapp.common.Result

internal interface MovieRepository {

    suspend fun loadMovies(): Result<List<Movie>>
    suspend fun loadMovie(movieId: Int): Result<Movie>
}