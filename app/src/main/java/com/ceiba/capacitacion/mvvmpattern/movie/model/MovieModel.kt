package com.ceiba.capacitacion.mvvmpattern.movie.model

import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.daos.MovieDaoRetrofit
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.dtos.toMovie
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.dtos.toMovieEntity
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.response.ApiEmptyResponse
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.response.ApiErrorResponse
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.response.ApiResponse
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.response.ApiSuccessResponse
import com.ceiba.capacitacion.mvvmpattern.movie.model.repository.MovieRepository
import javax.inject.Inject

class MovieModel @Inject constructor(
        private var movieRepository: MovieRepository,
        private var movieDaoRetrofit: MovieDaoRetrofit
) {

    private fun getAllMoviesFromNetwork(): List<Movie> {
        val response = movieDaoRetrofit.getAllMovies()
        return when (val apiResponse = ApiResponse.create(response)) {
            is ApiSuccessResponse -> {
                movieRepository.insertAll(apiResponse.body.items.toMovieEntity())
                apiResponse.body.items.toMovie()
            }
            is ApiEmptyResponse -> {
                emptyList()
            }
            is ApiErrorResponse -> {
                throw Exception()
            }
        }
    }

    fun getAllMovies(): List<Movie> {
        movieRepository.getAllMovies().let {
            return if (it.isNotEmpty()) {
                it
            } else {
                getAllMoviesFromNetwork()
            }
        }
    }
}