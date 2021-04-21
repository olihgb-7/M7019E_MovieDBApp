package com.ltu.m7019e.m7019e_moviedbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.m7019e_moviedbapp.database.MovieDatabaseDao
import com.ltu.m7019e.m7019e_moviedbapp.database.Movies
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieDatabaseDao: MovieDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movieList
        }

    init {
        _movieList.postValue(Movies().list)
    }

    fun getSavedMovies() {
        viewModelScope.launch {
            _movieList.value = movieDatabaseDao.getAllMovies()
        }
    }

    fun addMovie() {
        viewModelScope.launch {
            _movieList.value?.let { movieDatabaseDao.insert(it.get(0)) }
        }
    }
}