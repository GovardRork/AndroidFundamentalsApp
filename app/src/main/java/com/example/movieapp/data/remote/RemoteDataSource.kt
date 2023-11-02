package com.example.movieapp.data.remote

import com.example.movieapp.model.Movie

interface RemoteDataSource {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): Movie
}