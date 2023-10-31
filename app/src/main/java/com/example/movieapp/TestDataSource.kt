package com.example.movieapp

class TestDataSource {
    private val actors_1 = listOf(
        Actor(
            "https://www.ed92.org/wp-content/uploads/2021/12/Robert-Downey-Jr.-780x585.jpg",
            "Robert John Downey Jr."
        ),
        Actor(
            "https://hips.hearstapps.com/hmg-prod/images/chris-evans-gettyimages-1138769185.jpg",
            "Chris Evans"
        ),
        Actor(
            "https://cdn.britannica.com/97/219497-050-B1B58B0F/Mark-Ruffalo-2018.jpg",
            "Mark Ruffalo"
        ),
        Actor(
            "https://cdn.britannica.com/92/215392-050-96A4BC1D/Australian-actor-Chris-Hemsworth-2019.jpg",
            "Chris Hemsworth"
        )
    )

    val actors_2 = listOf(
        Actor(
            "https://www.ed92.org/wp-content/uploads/2021/12/Robert-Downey-Jr.-780x585.jpg",
            "Robert John Downey Jr."
        ),
        Actor(
            "https://hips.hearstapps.com/hmg-prod/images/chris-evans-gettyimages-1138769185.jpg",
            "Chris Evans"
        ),
        Actor(
            "https://cdn.britannica.com/97/219497-050-B1B58B0F/Mark-Ruffalo-2018.jpg",
            "Mark Ruffalo"
        ),
        Actor(
            "https://cdn.britannica.com/92/215392-050-96A4BC1D/Australian-actor-Chris-Hemsworth-2019.jpg",
            "Chris Hemsworth"
        ),
        Actor(
            "https://www.ed92.org/wp-content/uploads/2021/12/Robert-Downey-Jr.-780x585.jpg",
            "Robert John Downey Jr."
        ),
        Actor(
            "https://hips.hearstapps.com/hmg-prod/images/chris-evans-gettyimages-1138769185.jpg",
            "Chris Evans"
        ),
        Actor(
            "https://cdn.britannica.com/97/219497-050-B1B58B0F/Mark-Ruffalo-2018.jpg",
            "Mark Ruffalo"
        ),
        Actor(
            "https://cdn.britannica.com/92/215392-050-96A4BC1D/Australian-actor-Chris-Hemsworth-2019.jpg",
            "Chris Hemsworth"
        )
    )
    val actors_3 = listOf(
        Actor(
            "https://www.ed92.org/wp-content/uploads/2021/12/Robert-Downey-Jr.-780x585.jpg",
            "Robert John Downey Jr."
        ),
        Actor(
            "https://hips.hearstapps.com/hmg-prod/images/chris-evans-gettyimages-1138769185.jpg",
            "Chris Evans"
        ),
        Actor(
            "https://cdn.britannica.com/97/219497-050-B1B58B0F/Mark-Ruffalo-2018.jpg",
            "Mark Ruffalo"
        )
    )

    val m1: Movie = Movie(
        image = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        name = "Avatar",
        genre = "Comedy, Drama, Action",
        rating = 4.5,
        story = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
        reviews = 1239,
        durationInMin = 321,
        pg = 33,
        actors = actors_1
    )
    val m2:Movie = Movie(
        image = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        name = "Avatar:Legend of MutableList",
        story= "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
        genre = "Comedy, Drama, Action",
        rating = 4.5,
        reviews = 123, pg = 15,durationInMin = 33,actors = actors_2
       )
    val m3:Movie = Movie(
        image = "https://ae01.alicdn.com/kf/S4d1a7fc112bc4664a7e8c9aa11c0e130t.jpg",
        actors = actors_3,
        genre = "Comedy, Drama, Action, Fantasy, Documental",
        name = "Avatar for Facebook",
        story= "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
        pg = 22,
        rating = 5.0,
        reviews = 2333,
        durationInMin = 555)

    val MovieList = listOf (m1,m2,m3,m1,m2,m3,m1,m2,m3,m1,m2,m3)

}
