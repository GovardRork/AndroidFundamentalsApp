package com.example.movieapp

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val image: String,
    val name: String,
    val genre: String,
    val story: String,
    val rating: Double,
    val reviews: Int,
    val durationInMin: Int,
    val pg: Int,
    val actors: List<Actor>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(Actor)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(genre)
        parcel.writeString(story)
        parcel.writeDouble(rating)
        parcel.writeInt(reviews)
        parcel.writeInt(durationInMin)
        parcel.writeInt(pg)
        parcel.writeTypedList(actors)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}