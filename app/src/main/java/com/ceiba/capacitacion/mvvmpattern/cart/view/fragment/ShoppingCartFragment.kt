package com.ceiba.capacitacion.mvvmpattern.cart.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ceiba.capacitacion.mvvmpattern.R
import com.ceiba.capacitacion.mvvmpattern.cart.view.adapters.MovieAdapter
import com.ceiba.capacitacion.mvvmpattern.cart.view.adapters.setDataMovie
import com.ceiba.capacitacion.mvvmpattern.cart.viewmodel.ShoppingCartViewModel
import com.ceiba.capacitacion.mvvmpattern.shared.view.extension.showMessage
import com.ceiba.capacitacion.mvvmpattern.databinding.FragmentShoppingCartLayoutBinding
import com.ceiba.capacitacion.mvvmpattern.shared.view.extension.isHide
import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.Status
import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.response.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment : Fragment() {

    private val viewModel: ShoppingCartViewModel by viewModels()
    private lateinit var binding: FragmentShoppingCartLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeActionShoppingCart()
        updateListMovies()
        subscribeActionRemoveAllMovies()
    }

    private fun subscribeActionRemoveAllMovies() {
        with(binding) {
            fabRemoveAllMovies.setOnClickListener {
                loaderShopping.root.isHide(true)
                viewModel.deleteAllMovie()
                    .observe(viewLifecycleOwner, Observer { result ->
                        actionEventAddOrRemove(result)
                    })
            }
        }
    }

    private fun subscribeActionShoppingCart() {
        with(binding) {
            shoppingCartMovieList.adapter = MovieAdapter(
                { _, movieId ->
                    loaderShopping.root.isHide(true)
                    viewModel.deleteMovie(movieId)
                        .observe(viewLifecycleOwner, Observer { result ->
                            actionEventAddOrRemove(result)
                        })
                }, menuRes = R.menu.popup_menu_shopping
            )
        }
    }

    private fun <T> actionEventAddOrRemove(result: Resource<T>) {
        with(binding) {
            when (result.status) {
                Status.LOADING -> loaderShopping.root.isHide(false)
                Status.SUCCESS -> {
                    updateListMovies()
                    loaderShopping.root.isHide(true)
                }
                Status.ERROR -> loaderShopping.root.isHide(true)
            }
            if (result.status != Status.LOADING) {
                (result.message
                    ?: requireContext().getString(R.string.something_unexpected_happened)
                        ).showMessage(requireContext())
            }
        }
    }

    private fun updateListMovies() {
        with(binding) {
            loaderShopping.root.isHide(false)
            viewModel.shoppingCartMovies.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    Status.LOADING -> loaderShopping.root.isHide(false)
                    Status.SUCCESS -> {
                        shoppingCartMovieList.setDataMovie(result.data, listEmptyCart)
                        loaderShopping.root.isHide(true)
                    }
                    Status.ERROR -> {
                        shoppingCartMovieList.setDataMovie(result.data, listEmptyCart)
                        loaderShopping.root.isHide(true)
                        (result.message
                            ?: requireContext().getString(R.string.something_unexpected_happened)
                                ).showMessage(requireContext())
                    }
                }
            })
        }
    }
}