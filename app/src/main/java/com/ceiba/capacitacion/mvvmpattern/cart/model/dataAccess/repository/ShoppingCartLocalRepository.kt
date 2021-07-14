package com.lupesoft.appshoppingcenter.infrastructure.dblocal.repositorys

import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.dtos.toDomainModel
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.local.daos.ShoppingCartDao
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.local.entities.ShoppingCarEntity
import com.ceiba.capacitacion.mvvmpattern.cart.model.repository.ShoppingCartRepository
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import javax.inject.Inject

class ShoppingCartLocalRepository @Inject constructor(
        private val shoppingCartDao: ShoppingCartDao
) : ShoppingCartRepository {

    override fun getAllMoviesIntoShoppingCart(): List<Movie> {
        return shoppingCartDao.getAllMoviesIntoShoppingCart().toDomainModel()
    }

    override fun addMovie(idMovie: Int): Long {
        return shoppingCartDao.addMovie(ShoppingCarEntity(idMovie))
    }

    override fun deleteMovie(idMovie: Int): Int {
        return shoppingCartDao.deleteMovie(idMovie)
    }

    override fun deleteAllMovie(): Int {
        return shoppingCartDao.deleteAllMoviesIntoShoppingCart()
    }
}