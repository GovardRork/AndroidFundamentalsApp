package com.example.movieapp.model

import android.os.Parcel
import android.os.Parcelable

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
    val actors: List<Actor>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.createTypedArrayList(Genre),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Actor)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(pgAge)
        parcel.writeString(title)
        parcel.writeTypedList(genres)
        parcel.writeInt(reviewCount)
        parcel.writeInt(runningTime)
        parcel.writeByte(if (isLiked) 1 else 0)
        parcel.writeInt(rating)
        parcel.writeString(detailImageUrl)
        parcel.writeString(imageUrl)
        parcel.writeString(storyLine)
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