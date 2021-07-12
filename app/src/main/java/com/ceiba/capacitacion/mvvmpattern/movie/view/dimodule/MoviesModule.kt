package com.ceiba.capacitacion.mvvmpattern.movie.view.dimodule

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class MoviesModule {

    @Binds
    abstract fun provideMovieRepository(movieLocalRepository: com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.repository.MovieLocalRepository): com.ceiba.capacitacion.mvvmpattern.movie.model.repository.MovieRepository

    @Binds
    abstract fun provideShoppingCartRepository(shoppingCartLocalRepository: com.lupesoft.appshoppingcenter.infrastructure.dblocal.repositorys.ShoppingCartLocalRepository): com.ceiba.capacitacion.mvvmpattern.cart.model.repository.ShoppingCartRepository

}