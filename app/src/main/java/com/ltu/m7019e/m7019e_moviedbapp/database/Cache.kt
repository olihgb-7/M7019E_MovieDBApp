package com.ltu.m7019e.m7019e_moviedbapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie

@Dao
interface CacheDatabaseDao {

    @Query("select * from movies")
    fun getMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("DELETE FROM movies")
    fun deleteAll()
}

@Database(entities = [Movie::class], version = 1)
abstract class CachedDatabase: RoomDatabase() {
    abstract val cacheDatabaseDao: CacheDatabaseDao
}

private lateinit var INSTANCE: CachedDatabase

fun getDatabase(context: Context): CachedDatabase {
    synchronized(CachedDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CachedDatabase::class.java,
                "cached_movies"
            ).build()
        }
    }
    return INSTANCE
}