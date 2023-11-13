package com.example.movieapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.movieapp.presentation.movieDetails.MovieDetailsFragment
import com.example.movieapp.presentation.movieList.MovieListFragment

class MovieFragmentFactory: FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            MovieFragmentsEnum.MovieList.className -> MovieListFragment()
            MovieFragmentsEnum.MovieDetails.className -> MovieDetailsFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}

enum class MovieFragmentsEnum (val className: String) {
    MovieList(MovieListFragment::class.java.name),
    MovieDetails(MovieDetailsFragment::class.java.name)
}