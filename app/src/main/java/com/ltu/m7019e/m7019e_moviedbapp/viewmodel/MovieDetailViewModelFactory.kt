package com.ltu.m7019e.m7019e_moviedbapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ltu.m7019e.m7019e_moviedbapp.database.MovieDatabaseDao
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import java.lang.IllegalArgumentException

class MovieDetailViewModelFactory(private val movieDatabaseDao: MovieDatabaseDao,
                                  private val movieId: Long,
                                  private val application: Application,
                                  private val movie: Movie): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieDatabaseDao, movieId, application, movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}