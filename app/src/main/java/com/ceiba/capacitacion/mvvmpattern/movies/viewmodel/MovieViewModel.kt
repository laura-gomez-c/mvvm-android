package com.ceiba.capacitacion.mvvmpattern.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.ceiba.capacitacion.mvvmpattern.cart.model.models.ShoppingCartModel
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.models.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
        private val movieModel: MovieModel,
        private val shoppingCartModel: ShoppingCartModel
) : ViewModel() {

    val movies get() = movieModel.getAllMovies()

    fun addMovie(idMovie: Int) = shoppingCartModel.addMovie(idMovie)

    fun deleteMovie(idMovie: Int) = shoppingCartModel.deleteMovie(idMovie)
}
