package com.example.movieapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.screen.movieDetails.MovieDetailsViewModel
import com.example.movieapp.screen.movieList.MovieListViewModel

class ViewModelFactory:ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsViewModel::class.java -> MovieDetailsViewModel()
        MovieListViewModel::class.java -> MovieListViewModel()
        SharedViewModel::class.java -> SharedViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}