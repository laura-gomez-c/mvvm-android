package com.ceiba.capacitacion.mvvmpattern.movie.viewmodel

import androidx.lifecycle.ViewModel
import com.ceiba.capacitacion.mvvmpattern.cart.model.ShoppingCartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val shoppingCartModel: ShoppingCartModel
) : ViewModel() {

    val shoppingCartMovies
        get() = shoppingCartModel.getAllMoviesIntoShoppingCart()

    fun deleteMovie(idMovie: Int) = shoppingCartModel.deleteMovie(idMovie)

    fun deleteAllMovie() = shoppingCartModel.deleteAllMovie()
}