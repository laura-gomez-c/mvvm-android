package com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.entities.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<MovieEntity>)
}