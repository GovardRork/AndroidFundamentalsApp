package com.example.movieapp.screen.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.TestDataSource
import com.example.movieapp.model.Actor
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