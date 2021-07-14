package com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.dtos

import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.entities.MovieEntity
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.vos.MovieVo

fun List<MovieVo>.toMovieEntity(): List<MovieEntity> {
    return map {
        MovieEntity(
                it.id,
                it.adult,
                it.backdropPath,
                it.mediaType,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount)
    }
}

fun List<MovieVo>.toMovie(): List<Movie> {
    return map {
        Movie(
                it.adult,
                it.backdropPath,
                it.id,
                it.mediaType,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount)
    }
}