package com.ceiba.capacitacion.mvvmpattern.movie.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ceiba.capacitacion.mvvmpattern.R
import com.ceiba.capacitacion.mvvmpattern.cart.view.adapters.MovieAdapter
import com.ceiba.capacitacion.mvvmpattern.cart.view.adapters.setDataMovie
import com.ceiba.capacitacion.mvvmpattern.databinding.FragmentAllMoviesLayoutBinding
import com.ceiba.capacitacion.mvvmpattern.movie.viewmodel.MovieViewModel
import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.Status
import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.response.Resource
import com.ceiba.capacitacion.mvvmpattern.shared.view.extension.isHide
import com.ceiba.capacitacion.mvvmpattern.shared.view.extension.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMoviesFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: FragmentAllMoviesLayoutBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllMoviesLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeActionShoppingCart()
        subscribeUi()
    }

    private fun <T> actionEventAddOrRemove(result: Resource<T>) {
        with(binding) {
            when (result.status) {
                Status.LOADING -> loader.root.isHide(false)
                Status.SUCCESS -> loader.root.isHide(true)
                Status.ERROR -> loader.root.isHide(true)
            }
            if (result.status != Status.LOADING) {
                result.message?.let { requireContext().getString(it).showMessage(requireContext()) }
            }
        }
    }

    private fun subscribeActionShoppingCart() {
        with(binding) {
            binding.movieList.adapter = MovieAdapter({ addOrDelete, movieId ->
                loader.root.isHide(true)
                if (addOrDelete) {
                    viewModel.addMovie(movieId).observe(viewLifecycleOwner, Observer { result ->
                        actionEventAddOrRemove(result)
                    })
                } else {
                    viewModel.deleteMovie(movieId).observe(viewLifecycleOwner, Observer { result ->
                        actionEventAddOrRemove(result)
                    })
                }
            }, R.menu.popup_menu)
        }
    }

    private fun subscribeUi() {
        with(binding) {
            loader.root.isHide(false)
            viewModel.movies.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    Status.LOADING -> loader.root.isHide(false)
                    Status.SUCCESS -> {
                        movieList.setDataMovie(result.data, listEmpty)
                        loader.root.isHide(true)
                    }
                    Status.ERROR -> {
                        movieList.setDataMovie(null, listEmpty)
                        loader.root.isHide(true)
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}