package com.ltu.m7019e.m7019e_moviedbapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie

@Dao
interface MovieDatabaseDao {
    @Insert
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * from movies ORDER BY id ASC")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT EXISTS(SELECT * from movies WHERE id = :id)")
    suspend fun isFavourite(id: Long): Boolean
}