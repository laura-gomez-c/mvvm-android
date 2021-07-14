package com.ceiba.capacitacion.mvvmpattern.movie.viewmodel

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ceiba.capacitacion.mvvmpattern.R
import com.ceiba.capacitacion.mvvmpattern.cart.model.ShoppingCartModel
import com.ceiba.capacitacion.mvvmpattern.movie.model.MovieModel
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
class MovieViewModel @Inject constructor(
        private val movieModel: MovieModel,
        private val shoppingCartModel: ShoppingCartModel
) : ViewModel() {

    val movies get() = getAllMovies()

    fun addMovie(idMovie: Int): LiveData<Resource<Long>> {
        val flow = flow {
            emit(Resource.success(
                    shoppingCartModel.addMovie(idMovie),
                    message = R.string.add_successfully))
        }
        return flow
                .onStart { emit(Resource.loading(null, null)) }
                .catch { exception ->
                    with(exception) {
                        val msg = when (this) {
                            is SQLiteConstraintException -> R.string.movie_is_already
                            else -> R.string.something_unexpected_happened
                        }
                        emit(Resource.error(null, 0, msg))
                    }
                }
                .flowOn(Dispatchers.IO)
                .asLiveData()
    }

    fun deleteMovie(idMovie: Int): LiveData<Resource<Int>> {
        val flow = flow {
            emit(Resource.success(
                    shoppingCartModel.deleteMovie(idMovie),
                    message = R.string.remove_successfully))
        }
        return flow
                .onStart { emit(Resource.loading(null, null)) }
                .catch {
                    emit(Resource.error(
                            null,
                            0,
                            R.string.something_unexpected_happened))
                }
                .flowOn(Dispatchers.IO)
                .asLiveData()
    }

    private fun getAllMovies(): LiveData<Resource<List<Movie>>> {
        val flow = flow {
            emit(Resource.success(movieModel.getAllMovies()))
        }
        return flow
                .onStart { emit(Resource.loading(null, null)) }
                .catch {
                    emit(Resource.error(
                            null,
                            0,
                            R.string.something_unexpected_happened))
                }
                .flowOn(Dispatchers.IO)
                .asLiveData()
    }
}
