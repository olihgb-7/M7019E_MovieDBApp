package com.ltu.m7019e.m7019e_moviedbapp.network

import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import com.ltu.m7019e.m7019e_moviedbapp.model.MovieGenre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieDetailResponse {
    @Json(name = "id")
    var id: Long = 0

    @Json(name = "genres")
    var genres: List<MovieGenre> = listOf()

    @Json(name = "homepage")
    var homepage: String = ""

    @Json(name = "imdb_id")
    var imdb_id: String = ""
}