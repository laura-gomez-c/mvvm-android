package com.lupesoft.appshoppingcenter.infrastructure.dblocal.repositorys

import com.ceiba.capacitacion.mvvmpattern.cart.model.repositories.ShoppingCartRepository
import com.ceiba.capacitacion.mvvmpattern.movies.model.dataAccess.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.daos.ShoppingCartDao
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.dtos.toDomainModel
import com.ceiba.capacitacion.mvvmpattern.cart.model.dataAccess.entities.ShoppingCarEntity
import javax.inject.Inject

class ShoppingCartRepositoryRoom @Inject constructor(
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