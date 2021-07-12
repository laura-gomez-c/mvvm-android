package com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.daos

import com.ceiba.capacitacion.mvvmpattern.BuildConfig.ApiKey
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.vos.MoviesVo
import retrofit2.Response
import retrofit2.http.GET

interface MovieDaoRetrofit {
    @GET("list/3?api_key=${ApiKey}&language=en-US")
    suspend fun getAllMovies(): Response<MoviesVo>
}