package com.ceiba.capacitacion.mvvmpattern.shared.view.dimodule

import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.Api
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.remote.daos.MovieDaoRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideMovieDaoRetrofit(): MovieDaoRetrofit = Api.create()
}