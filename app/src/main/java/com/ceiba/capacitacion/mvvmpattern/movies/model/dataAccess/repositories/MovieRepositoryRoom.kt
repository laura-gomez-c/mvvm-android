package com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.repositories

import com.ceiba.capacitacion.mvvmpattern.movies.model.repositories.MovieRepository
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.dao.MovieDao
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.dtos.toDomainModel
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.entities.MovieEntity
import javax.inject.Inject

class MovieRepositoryRoom @Inject constructor(
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies().toDomainModel()
    }

    override fun insertAll(entities: List<MovieEntity>) {
        return movieDao.insertAll(entities)
    }
}