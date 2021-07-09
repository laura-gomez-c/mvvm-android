package com.ceiba.capacitacion.mvvmpattern.movies.view.dimodule

import com.ceiba.capacitacion.mvvmpattern.movies.model.repositories.MovieRepository
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.repositories.MovieRepositoryRoom
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.repositorys.ShoppingCartRepositoryRoom
import com.ceiba.capacitacion.mvvmpattern.cart.model.repositories.ShoppingCartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class MoviesModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryRoom: MovieRepositoryRoom): MovieRepository

    @Binds
    abstract fun provideShoppingCartRepository(shoppingCartRepositoryRoom: ShoppingCartRepositoryRoom): ShoppingCartRepository

}