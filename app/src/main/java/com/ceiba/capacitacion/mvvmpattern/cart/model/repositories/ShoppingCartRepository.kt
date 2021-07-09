package com.ceiba.capacitacion.mvvmpattern.cart.model.repositories

import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.vo.Movie

interface ShoppingCartRepository {

    fun getAllMoviesIntoShoppingCart(): List<Movie>

    fun addMovie(idMovie: Int): Long

    fun deleteMovie(idMovie: Int): Int

    fun deleteAllMovie(): Int
}