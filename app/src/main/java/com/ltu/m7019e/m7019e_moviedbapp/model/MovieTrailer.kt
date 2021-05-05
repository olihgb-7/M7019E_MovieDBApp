package com.ltu.m7019e.m7019e_moviedbapp.model

import com.squareup.moshi.Json

data class MovieTrailer (
    @Json(name = "id")
    var trailer_id: String,

    @Json(name = "name")
    var name: String,

    @Json(name = "key")
    var key: String,

    @Json(name = "site")
    var site: String
)