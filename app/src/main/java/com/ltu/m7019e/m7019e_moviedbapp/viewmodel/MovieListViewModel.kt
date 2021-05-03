package com.ltu.m7019e.m7019e_moviedbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.m7019e_moviedbapp.database.MovieDatabaseDao
import com.ltu.m7019e.m7019e_moviedbapp.database.Movies
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import com.ltu.m7019e.m7019e_moviedbapp.network.DataFetchStatus
import com.ltu.m7019e.m7019e_moviedbapp.network.MovieResponse
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieDatabaseDao: MovieDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movieList
        }

    private val _navigateToMovieDetail = MutableLiveData<Movie>()
    val navigateToMovieDetail: LiveData<Movie>
        get() {
            return _navigateToMovieDetail
        }

    init {
        getPopularMovies()
        _dataFetchStatus.value = DataFetchStatus.LOADING
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            try{
                val movieResponse: MovieResponse = TMDBApi.movieListRetrofitService.getPopularMovies()
                _movieList.value = movieResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
            }
            catch (e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _movieList.value = arrayListOf()
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            try{
                val movieResponse: MovieResponse = TMDBApi.movieListRetrofitService.getTopRatedMovies()
                _movieList.value = movieResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
            }
            catch (e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _movieList.value = arrayListOf()
            }
        }
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

    fun onMovieListItemClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }
}