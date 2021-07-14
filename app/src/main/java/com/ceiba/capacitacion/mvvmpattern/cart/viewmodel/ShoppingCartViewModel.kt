package com.ceiba.capacitacion.mvvmpattern.cart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ceiba.capacitacion.mvvmpattern.R
import com.ceiba.capacitacion.mvvmpattern.cart.model.ShoppingCartModel
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
        private val shoppingCartModel: ShoppingCartModel
) : ViewModel() {

    val shoppingCartMovies
        get() = getAllMoviesIntoShoppingCart()

    private fun getAllMoviesIntoShoppingCart(): LiveData<Resource<List<Movie>>> {
        val flow = flow {
            emit(
                    Resource.success(data = shoppingCartModel.getAllMoviesIntoShoppingCart())
            )
        }
        return flow
                .onStart { emit(Resource.loading(null, 0)) }
                .catch {
                    emit(
                            Resource.error(
                                    null,
                                    0,
                                    R.string.something_unexpected_happened
                            )
                    )
                }
                .flowOn(Dispatchers.IO)
                .asLiveData()
    }

    fun deleteMovie(idMovie: Int): LiveData<Resource<Int>> {
        val flow = flow {
            emit(
                    Resource.success(
                            shoppingCartModel.deleteMovie(idMovie),
                            message = R.string.remove_successfully
                    )
            )
        }
        return flow
                .onStart { emit(Resource.loading(null, null)) }
                .catch {
                    emit(
                            Resource.error(
                                    null,
                                    0,
                                    R.string.something_unexpected_happened
                            )
                    )
                }
                .flowOn(Dispatchers.IO)
                .asLiveData()
    }

    fun deleteAllMovie(): LiveData<Resource<Int>> {
        val flow = flow {
            emit(
                    Resource.success(
                            shoppingCartModel.deleteAllMovie(),
                            message = R.string.remove_all_successfully
                    )
            )
        }
        return flow
                .onStart { emit(Resource.loading(null, null)) }
                .catch {
                    emit(
                            Resource.error(
                                    null,
                                    0,
                                    R.string.something_unexpected_happened
                            )
                    )
                }
                .flowOn(Dispatchers.IO)
                .asLiveData()
    }
}