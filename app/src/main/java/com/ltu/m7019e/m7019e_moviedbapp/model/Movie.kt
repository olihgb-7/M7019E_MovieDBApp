package com.ltu.m7019e.m7019e_moviedbapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var overview: String
): Parcelable