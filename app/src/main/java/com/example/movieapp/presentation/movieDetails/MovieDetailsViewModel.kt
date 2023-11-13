package com.example.movieapp.presentation.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.Actor
import com.example.movieapp.model.TestDataSource
import kotlinx.coroutines.launch

class MovieDetailsViewModel:ViewModel() {
    private var _actorsStream = MutableLiveData<List<Actor>?>()
    val actorsStream = _actorsStream

    fun loadActors(movieId:Int){
        viewModelScope.launch {
            _actorsStream.value = TestDataSource().movieDetailsList.find { it.id==movieId }?.actors
        }
    }
}