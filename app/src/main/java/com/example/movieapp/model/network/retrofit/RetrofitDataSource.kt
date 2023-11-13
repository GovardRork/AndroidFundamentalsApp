package com.example.movieapp.model.network.retrofit

import com.example.movieapp.model.Actor
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Movie
import com.example.movieapp.model.TestDataSource
import com.example.movieapp.model.network.MovieApiService

private const val ADULT_AGE = 16
private const val CHILD_AGE = 13

internal class RetrofitDataSource(
    private val api: MovieApiService,
    private val imageUrlAppender: ImageUrlAppender,
) : RemoteDataSource {

    override suspend fun loadMovies(): List<Movie> {
        val genres = api.loadGenres().genres
        // TODO пагинация + fix fields
        return api.loadUpcoming(page = 1).results.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                imageUrl = imageUrlAppender.getMoviePosterImageUrl(movie.posterPath),
                detailImageUrl = imageUrlAppender.getMoviePosterImageUrl(movie.posterPath),//fix
                rating = movie.voteAverage.toInt(),
                reviewCount = movie.voteCount,
                pgAge = movie.adult.toSpectatorAge(),
                runningTime = 100,//fix
                isLiked = false,
                genres = genres
                    .filter { genreResponse -> movie.genreIds.contains(genreResponse.id) }
                    .map { genre -> Genre(genre.id, genre.name) },
                storyLine = "",//fix
                actors = TestDataSource().actors_2 //fix
            )
        }
    }

    override suspend fun loadMovie(movieId: Int): Movie {
        val details = api.loadMovieDetails(movieId)

        return Movie(
            id = details.id,
            pgAge = details.adult.toSpectatorAge(),
            title = details.title,
            genres = details.genres.map { Genre(it.id, it.name) },
            reviewCount = details.revenue,
            runningTime = details.runtime?:0,
            isLiked = false,
            rating = details.popularity.toInt(),
            detailImageUrl = imageUrlAppender.getDetailImageUrl(details.backdropPath),
            imageUrl = imageUrlAppender.getDetailImageUrl(details.backdropPath),
            storyLine = details.overview.orEmpty(),
            actors = api.loadMovieCredits(movieId).casts.map { actor ->
                Actor(
                    id = actor.id,
                    name = actor.name,
                    imageUrl = imageUrlAppender.getActorImageUrl(actor.profilePath)
                )
            }
        )
    }

    private fun Boolean.toSpectatorAge(): Int = if (this) ADULT_AGE else CHILD_AGE

}