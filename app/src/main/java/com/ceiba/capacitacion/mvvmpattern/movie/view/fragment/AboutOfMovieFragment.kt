package com.ceiba.capacitacion.mvvmpattern.movie.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ceiba.capacitacion.mvvmpattern.R
import com.ceiba.capacitacion.mvvmpattern.databinding.FragmentAboutOfMovieLayoutBinding
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.movie.viewmodel.MovieViewModel
import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.Status
import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.response.Resource
import com.ceiba.capacitacion.mvvmpattern.shared.view.extension.isHide
import com.ceiba.capacitacion.mvvmpattern.shared.view.extension.loadImageDetail
import com.ceiba.capacitacion.mvvmpattern.shared.view.extension.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutOfMovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: FragmentAboutOfMovieLayoutBinding


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutOfMovieLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeClick()
    }

    private fun subscribeClick() {
        with(binding) {
            loaderAbout.root.isHide(false)
            val movie = (arguments?.get("movie") as Movie)
            setDataMovie(movie)
            fabActionShoppingCart.setOnClickListener { showMenu(it, movie) }
        }
    }

    private fun setDataMovie(movie: Movie) {
        with(binding) {
            movie.let {
                imageMovie.loadImageDetail(it.posterPath)
                itemName.text = it.title
                itemDescription.text = it.overview
                itemPopularity.text = it.popularity.toString()
            }
            loaderAbout.root.isHide(true)
        }
    }

    private fun showMenu(v: View, movie: Movie) {
        PopupMenu(requireContext(), v).apply {
            menuInflater.inflate(R.menu.popup_menu_detail, menu)
            setOnMenuItemClickListener { menuItem: MenuItem ->
                movie.let { data ->
                    when (menuItem.itemId) {
                        R.id.add_movie_detail -> {
                            viewModel.addMovie(data.id)
                                    .observe(viewLifecycleOwner, Observer { result ->
                                        actionEventAddOrRemove(result)
                                    })
                            true
                        }
                        R.id.delete_movie_detail -> {
                            viewModel.deleteMovie(data.id)
                                    .observe(viewLifecycleOwner, Observer { result ->
                                        actionEventAddOrRemove(result)
                                    })
                            true
                        }
                        else -> false
                    }
                }
            }
        }.also { it.show() }
    }

    private fun <T> actionEventAddOrRemove(result: Resource<T>) {
        with(binding) {
            when (result.status) {
                Status.LOADING -> loaderAbout.root.isHide(false)
                Status.SUCCESS -> loaderAbout.root.isHide(true)
                Status.ERROR -> loaderAbout.root.isHide(true)
            }
            if (result.status != Status.LOADING) {
                result.message?.let { requireContext().getString(it).showMessage(requireContext()) }
            }
        }
    }
}