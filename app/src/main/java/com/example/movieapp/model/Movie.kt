package com.example.movieapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String?,
    val genres: List<Genre>?,
    val reviewCount: Int,
    val runningTime: Int,
    val isLiked: Boolean,
    val rating: Int,
    val detailImageUrl: String?,
    val imageUrl: String?,
    val storyLine: String?,
    val actors: List<Actor>?):Parcelable