package com.ltu.m7019e.m7019e_moviedbapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MovieReviewsAndTrailersViewModelFactory(private val movieId: Long, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieReviewsAndTrailersViewModel::class.java)) {
            return MovieReviewsAndTrailersViewModel(movieId, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}