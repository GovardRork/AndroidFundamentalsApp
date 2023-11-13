package com.example.movieapp.model.network.retrofit

import com.example.movieapp.model.Movie

interface RemoteDataSource {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): Movie
}