package com.ltu.m7019e.m7019e_moviedbapp.model

import com.squareup.moshi.Json

data class MovieGenre (
    @Json(name = "name")
    var name: String
)