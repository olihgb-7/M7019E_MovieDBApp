package com.ltu.m7019e.m7019e_moviedbapp.model

import com.squareup.moshi.Json

data class MovieReview (
    @Json(name = "id")
    var review_id: String,

    @Json(name = "author")
    var author: String,

    @Json(name = "content")
    var content: String
)