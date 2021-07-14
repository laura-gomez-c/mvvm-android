package com.ceiba.capacitacion.mvvmpattern.shared.view.dimodule

import android.content.Context
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.local.daos.ShoppingCartDao
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.dao.MovieDao
import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
            AppDataBase.getInstance(context)

    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): MovieDao = appDataBase.movieDao()

    @Provides
    fun provideShoppingCartDao(appDataBase: AppDataBase): ShoppingCartDao =
            appDataBase.shoppingCartDao()
}