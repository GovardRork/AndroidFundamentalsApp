package com.example.movieapp

import com.example.movieapp.model.Actor
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Movie

class TestDataSource {
    private val actors_1 = listOf(
        Actor(1,
            "https://www.ed92.org/wp-content/uploads/2021/12/Robert-Downey-Jr.-780x585.jpg",
            "Robert John Downey Jr."
        ),
        Actor(2,
            "https://hips.hearstapps.com/hmg-prod/images/chris-evans-gettyimages-1138769185.jpg",
            "Chris Evans"
        ),
        Actor(3,
            "https://cdn.britannica.com/97/219497-050-B1B58B0F/Mark-Ruffalo-2018.jpg",
            "Mark Ruffalo"
        ),
        Actor(4,
            "https://cdn.britannica.com/92/215392-050-96A4BC1D/Australian-actor-Chris-Hemsworth-2019.jpg",
            "Chris Hemsworth"
        )
    )

    val actors_2 = listOf(
        Actor(5,
            "https://www.ed92.org/wp-content/uploads/2021/12/Robert-Downey-Jr.-780x585.jpg",
            "Robert John Downey Jr."
        ),
        Actor(6,
            "https://hips.hearstapps.com/hmg-prod/images/chris-evans-gettyimages-1138769185.jpg",
            "Chris Evans"
        ),
        Actor(7,
            "https://cdn.britannica.com/97/219497-050-B1B58B0F/Mark-Ruffalo-2018.jpg",
            "Mark Ruffalo"
        ),
        Actor(8,
            "https://cdn.britannica.com/92/215392-050-96A4BC1D/Australian-actor-Chris-Hemsworth-2019.jpg",
            "Chris Hemsworth"
        ),
        Actor(9,
            "https://www.ed92.org/wp-content/uploads/2021/12/Robert-Downey-Jr.-780x585.jpg",
            "Robert John Downey Jr."
        ),
        Actor(10,
            "https://hips.hearstapps.com/hmg-prod/images/chris-evans-gettyimages-1138769185.jpg",
            "Chris Evans"
        ),
        Actor(11,
            "https://cdn.britannica.com/97/219497-050-B1B58B0F/Mark-Ruffalo-2018.jpg",
            "Mark Ruffalo"
        ),
        Actor(12,
            "https://cdn.britannica.com/92/215392-050-96A4BC1D/Australian-actor-Chris-Hemsworth-2019.jpg",
            "Chris Hemsworth"
        )
    )
    val actors_3 = listOf(
        Actor(1,
            "https://www.ed92.org/wp-content/uploads/2021/12/Robert-Downey-Jr.-780x585.jpg",
            "Robert John Downey Jr."
        ),
        Actor(2,
            "https://hips.hearstapps.com/hmg-prod/images/chris-evans-gettyimages-1138769185.jpg",
            "Chris Evans"
        ),
        Actor(3,
            "https://cdn.britannica.com/97/219497-050-B1B58B0F/Mark-Ruffalo-2018.jpg",
            "Mark Ruffalo"
        )
    )

    val g1 = listOf(
        Genre(1,"Comedy"),
        Genre(2,"Drama"),
        Genre(3,"Action"),
        Genre(4,"Fantasy"),
        Genre(5,"Documental")
    )
    val g2 = listOf(
        Genre(1,"Comedy"),
        Genre(2,"Drama"),
        Genre(3,"Action")
    )
    val g3 = listOf(
        Genre(1,"Comedy"),
        Genre(2,"Drama")
    )
    val g4 = listOf(
        Genre(5,"Documental")
    )


    val m1: Movie = Movie(
        detailImageUrl = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        imageUrl = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        title = "Avatar",
        genres = g1,
        rating = 3,
        storyLine = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
        reviewCount = 1239,
        runningTime = 321,
        pgAge = 33,
        actors = actors_1,
        isLiked = false,
        id = 1
    )
    val m2:Movie = Movie(
        detailImageUrl = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        imageUrl = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        title = "Avatar:Legend of MutableList",
        storyLine = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
        genres = g2,
        rating = 7,
        reviewCount = 123,
        pgAge = 15,
        runningTime = 33,
        actors = actors_2,
        isLiked = false,
        id = 2
       )
    val m3:Movie = Movie(
        detailImageUrl = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        imageUrl = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        actors = actors_3,
        genres = g3,
        title = "Avatar for Facebook",
        storyLine = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
        pgAge = 22,
        rating = 10,
        reviewCount = 2333,
        runningTime = 555,
        isLiked = false,
        id = 2
    )
    val m4:Movie = Movie(
        detailImageUrl = "",
        imageUrl = "",
        actors = emptyList<Actor>(),
        genres = g4,
        title = "EMPTY ACTORS MOVIE",
        storyLine = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
        pgAge = 18,
        rating = 1,
        reviewCount = 5,
        runningTime = 3,
        isLiked = false,
        id = 2
    )

    val movieDetailsList = listOf (m1,m2,m3,m4,m2,m3,m1,m4,m3,m1,m2,m4)

}
