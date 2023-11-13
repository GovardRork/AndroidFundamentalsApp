package com.example.movieapp.model.network.retrofit.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UpComingResponse(
    @SerialName("page") val page : Int,
    @SerialName("results") val results : List<MovieResponse>
)