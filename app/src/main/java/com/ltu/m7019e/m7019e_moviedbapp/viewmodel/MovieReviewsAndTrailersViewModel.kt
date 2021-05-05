package com.ltu.m7019e.m7019e_moviedbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import com.ltu.m7019e.m7019e_moviedbapp.model.MovieReview
import com.ltu.m7019e.m7019e_moviedbapp.model.MovieTrailer
import com.ltu.m7019e.m7019e_moviedbapp.network.MovieReviewResponse
import com.ltu.m7019e.m7019e_moviedbapp.network.MovieTrailerResponse
import kotlinx.coroutines.launch

class MovieReviewsAndTrailersViewModel(movieId: Long, application: Application) : AndroidViewModel(application) {

    var id = movieId

    private val _reviewList = MutableLiveData<List<MovieReview>>()
    val reviewList: LiveData<List<MovieReview>>
        get() {
            return _reviewList
        }

    private val _trailerList = MutableLiveData<List<MovieTrailer>>()
    val trailerList: LiveData<List<MovieTrailer>>
        get() {
            return _trailerList
        }

    private val _navigateToTrailerLink = MutableLiveData<MovieTrailer>()
    val navigateToTrailerLink: LiveData<MovieTrailer>
        get() {
            return _navigateToTrailerLink
        }

    init {
        getMovieReviews()
        getMovieTrailers()
    }

    private fun getMovieReviews() {
        viewModelScope.launch {
            val movieReviewResponse: MovieReviewResponse = TMDBApi.movieListRetrofitService.getMovieReviews("$id/reviews")
            _reviewList.value = movieReviewResponse.results
        }
    }

    private fun getMovieTrailers() {
        viewModelScope.launch {
            val movieTrailerResponse: MovieTrailerResponse = TMDBApi.movieListRetrofitService.getMovieTrailers("$id/videos")
            _trailerList.value = movieTrailerResponse.results
        }
    }

    fun onTrailerItemClicked(movieTrailer: MovieTrailer) {
        _navigateToTrailerLink.value = movieTrailer
    }

    fun onTrailerNavigated() {
        _navigateToTrailerLink.value = null
    }
}