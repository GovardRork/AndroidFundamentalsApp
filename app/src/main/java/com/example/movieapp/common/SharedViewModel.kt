package com.example.movieapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.TransactionFragmentClicks
import com.example.movieapp.model.Actor
import com.example.movieapp.model.Movie

class SharedViewModel : ViewModel() {
    private val _data = MutableLiveData<Any?>()
    val data: LiveData<Any?>
        get() = _data
    var movie:Movie? = null
    val emptyMovie = Movie(detailImageUrl = "",
    imageUrl = "",
    actors = emptyList(),
    genres = null,
    title = "EMPTY ACTORS MOVIE",
    storyLine = null,
    pgAge = 0,
    rating = 0,
    reviewCount = 0,
    runningTime = 0,
    isLiked = false,
    id = -1)

    val emptyActor = Actor(
        imageUrl = null,
        name = null,
        id = -1)

    var listener: TransactionFragmentClicks? = null

    fun setData(data: Any?) {
        _data.value = data
    }
}