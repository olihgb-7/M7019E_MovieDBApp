package com.ltu.m7019e.m7019e_moviedbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltu.m7019e.m7019e_moviedbapp.database.Movies
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movieList
        }

    init {
        _movieList.postValue(Movies().list)
    }
}