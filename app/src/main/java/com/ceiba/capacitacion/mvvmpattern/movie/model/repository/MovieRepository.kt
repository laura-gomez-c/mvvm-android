package com.ceiba.capacitacion.mvvmpattern.movie.model.repository

import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.entities.MovieEntity

interface MovieRepository {

    fun getAllMovies(): List<Movie>

    fun insertAll(entities: List<MovieEntity>)
}