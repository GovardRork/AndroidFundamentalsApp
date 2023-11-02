package com.example.movieapp.data

import com.example.movieapp.common.Result
import com.example.movieapp.common.runCatchingResult
import com.example.movieapp.data.remote.RemoteDataSource
import com.example.movieapp.domain.MovieRepository
import com.example.movieapp.model.Movie

internal class MovieRepositoryImpl(private val remoteDataResource: RemoteDataSource) : MovieRepository {

    override suspend fun loadMovies(): Result<List<Movie>> {
        return runCatchingResult { remoteDataResource.loadMovies() }
    }

    override suspend fun loadMovie(movieId: Int): Result<Movie> {
        return runCatchingResult { remoteDataResource.loadMovie(movieId) }
    }
}