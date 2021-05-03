package com.ltu.m7019e.m7019e_moviedbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.m7019e_moviedbapp.model.MovieGenre
import com.ltu.m7019e.m7019e_moviedbapp.network.MovieDetailResponse
import kotlinx.coroutines.launch

class MovieDetailViewModel(movieId: Long, application: Application) : AndroidViewModel(application) {

    var id = movieId

    private val _movieGenres = MutableLiveData<List<MovieGenre>>()
    val movieGenres: LiveData<List<MovieGenre>>
        get() {
            return _movieGenres
        }

    private val _movieHomepage = MutableLiveData<String>()
    val movieHomepage: LiveData<String>
        get() {
            return _movieHomepage
        }

    private val _movieImdbId = MutableLiveData<String>()
    val movieImdbId: LiveData<String>
        get() {
            return _movieImdbId
        }

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() {
        viewModelScope.launch {
            val movieDetailResponse: MovieDetailResponse = TMDBApi.movieListRetrofitService.getMovieDetails("$id")
            _movieGenres.value = movieDetailResponse.genres
            _movieHomepage.value = movieDetailResponse.homepage
            _movieImdbId.value = movieDetailResponse.imdb_id
        }
    }
}