package com.ltu.m7019e.m7019e_moviedbapp.network

import com.ltu.m7019e.m7019e_moviedbapp.model.MovieTrailer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieTrailerResponse {
    @Json(name = "id")
    var id: Long = 0

    @Json(name = "results")
    var results: List<MovieTrailer> = listOf()
}