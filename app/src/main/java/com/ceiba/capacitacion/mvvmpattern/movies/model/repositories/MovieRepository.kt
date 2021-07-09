package com.ceiba.capacitacion.mvvmpattern.movies.model.repositories

import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.entities.MovieEntity

interface MovieRepository {

    fun getAllMovies(): List<Movie>

    fun insertAll(entities: List<MovieEntity>)
}