package com.ltu.m7019e.m7019e_moviedbapp.repository

import androidx.lifecycle.LiveData
import com.ltu.m7019e.m7019e_moviedbapp.database.CachedDatabase
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val database: CachedDatabase) {

    val movies: LiveData<List<Movie>> = database.cacheDatabaseDao.getMovies()

    suspend fun refreshPopularMovies() {
        withContext(Dispatchers.IO) {
            val popularMovies = TMDBApi.movieListRetrofitService.getPopularMovies()
            database.cacheDatabaseDao.deleteAll()
            database.cacheDatabaseDao.insertAll(popularMovies.results)
        }
    }

    suspend fun refreshTopRatedMovies() {
        withContext(Dispatchers.IO) {
            val topRatedMovies = TMDBApi.movieListRetrofitService.getTopRatedMovies()
            database.cacheDatabaseDao.deleteAll()
            database.cacheDatabaseDao.insertAll(topRatedMovies.results)
        }
    }

}