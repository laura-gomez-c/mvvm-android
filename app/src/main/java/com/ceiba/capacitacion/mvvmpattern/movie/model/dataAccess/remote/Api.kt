package com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote

import com.ceiba.capacitacion.mvvmpattern.BuildConfig.BaseUrl
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.daos.MovieDaoRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    fun create(): MovieDaoRetrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

        return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieDaoRetrofit::class.java)
    }
}
