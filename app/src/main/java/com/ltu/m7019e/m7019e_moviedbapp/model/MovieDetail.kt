package com.ltu.m7019e.m7019e_moviedbapp.model

data class MovieDetail (
    var genres: List<MovieGenre>,
    var homepage: String,
    var imdb_id: String
)