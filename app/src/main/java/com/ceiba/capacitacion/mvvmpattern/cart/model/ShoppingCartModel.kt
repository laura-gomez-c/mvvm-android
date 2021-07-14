package com.ceiba.capacitacion.mvvmpattern.cart.model

import com.ceiba.capacitacion.mvvmpattern.cart.model.repository.ShoppingCartRepository
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import javax.inject.Inject

class ShoppingCartModel @Inject constructor(private val shoppingCartRepository: ShoppingCartRepository) {

    var movie: Movie? = null

    fun getAllMoviesIntoShoppingCart(): List<Movie> {
        return shoppingCartRepository.getAllMoviesIntoShoppingCart()
    }

    fun addMovie(idMovie: Int): Long {
        return shoppingCartRepository.addMovie(idMovie);
    }

    fun deleteMovie(idMovie: Int): Int {
        return shoppingCartRepository.deleteMovie(idMovie);
    }

    fun deleteAllMovie(): Int {
        return shoppingCartRepository.deleteAllMovie()
    }
}