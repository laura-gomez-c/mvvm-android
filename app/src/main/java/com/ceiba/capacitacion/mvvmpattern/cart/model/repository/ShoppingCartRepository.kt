package com.ceiba.capacitacion.mvvmpattern.cart.model.repository

import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie

interface ShoppingCartRepository {

    fun getAllMoviesIntoShoppingCart(): List<Movie>

    fun addMovie(idMovie: Int): Long

    fun deleteMovie(idMovie: Int): Int

    fun deleteAllMovie(): Int
}