package com.example.movieapp.model

import com.example.movieapp.common.Result
import com.example.movieapp.common.runCatchingResult
import com.example.movieapp.model.network.retrofit.RemoteDataSource

internal class MovieRepositoryImpl(private val remoteDataResource: RemoteDataSource) :
    MovieRepository {

    override suspend fun loadMovies(): Result<List<Movie>> {
        return runCatchingResult { remoteDataResource.loadMovies() }
    }

    override suspend fun loadMovie(movieId: Int): Result<Movie> {
        return runCatchingResult { remoteDataResource.loadMovie(movieId) }
    }
}