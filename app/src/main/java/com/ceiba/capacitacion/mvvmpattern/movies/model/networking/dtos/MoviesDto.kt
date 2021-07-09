package com.ceiba.capacitacion.mvvmpattern.movies.model.networking.dtos

import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.entities.MovieEntity
import com.ceiba.capacitacion.mvvmpattern.movies.model.networking.vos.MovieVo

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