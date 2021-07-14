package com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.repository

import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.dtos.toDomainModel
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.dao.MovieDao
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.entities.MovieEntity
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.movie.model.repository.MovieRepository
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(
        private val movieDao: MovieDao
) : MovieRepository {

    override fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies().toDomainModel()
    }

    override fun insertAll(entities: List<MovieEntity>) {
        return movieDao.insertAll(entities)
    }
}