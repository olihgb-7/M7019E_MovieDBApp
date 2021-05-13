package com.ltu.m7019e.m7019e_moviedbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.m7019e_moviedbapp.database.MovieDatabaseDao
import com.ltu.m7019e.m7019e_moviedbapp.database.Movies
import com.ltu.m7019e.m7019e_moviedbapp.database.getDatabase
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import com.ltu.m7019e.m7019e_moviedbapp.network.DataFetchStatus
import com.ltu.m7019e.m7019e_moviedbapp.network.MovieResponse
import com.ltu.m7019e.m7019e_moviedbapp.repository.MovieRepository
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class MovieListViewModel(private val movieDatabaseDao: MovieDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository(getDatabase(application))

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private var _savedMovieList =  MutableLiveData<List<Movie>>()
    val savedMovieList: LiveData<List<Movie>>
        get() {
            return _savedMovieList
        }

    private var _cachedMovieList: LiveData<List<Movie>> = movieRepository.movies
    val cachedMovieList: LiveData<List<Movie>>
        get() {
            return _cachedMovieList
        }

    private val _navigateToMovieDetail = MutableLiveData<Movie>()
    val navigateToMovieDetail: LiveData<Movie>
        get() {
            return _navigateToMovieDetail
        }

    init {
        _dataFetchStatus.value = DataFetchStatus.LOADING
    }

    fun getSavedMovies() {
        viewModelScope.launch {
            _savedMovieList.value = movieDatabaseDao.getAllMovies()
        }
    }

    fun refreshPopularMoviesFromRepository() {
        viewModelScope.launch {
            try {
                movieRepository.refreshPopularMovies()
                _dataFetchStatus.value = DataFetchStatus.DONE
            }
            catch (e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
            }
        }
    }

    fun refreshTopRatedMoviesFromRepository() {
        viewModelScope.launch {
            try {
                movieRepository.refreshTopRatedMovies()
                _dataFetchStatus.value = DataFetchStatus.DONE
            }
            catch (e: Exception) {

                _dataFetchStatus.value = DataFetchStatus.ERROR
            }
        }
    }

    fun onMovieListItemClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }
}