package com.ceiba.capacitacion.mvvmpattern.shared.view.dimodule

import com.ceiba.capacitacion.mvvmpattern.movies.model.networking.Api
import com.ceiba.capacitacion.mvvmpattern.movies.model.networking.daos.MovieDaoRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideMovieDaoRetrofit(): MovieDaoRetrofit = Api.create()
}