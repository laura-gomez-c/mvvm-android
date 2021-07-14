package com.ceiba.capacitacion.mvvmpattern.cart.view.adapters.holder

import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.capacitacion.mvvmpattern.R
import com.ceiba.capacitacion.mvvmpattern.databinding.ItemMovieBinding
import com.ceiba.capacitacion.mvvmpattern.movie.model.dataAccess.local.vo.Movie
import com.ceiba.capacitacion.mvvmpattern.shared.view.extension.loadImage

class ShoppingCartViewHolder(
        private val actionShoppingCart: (addOrDelete: Boolean, movieId: Int) -> Unit,
        @MenuRes private val menuRes: Int,
        private val binding: ItemMovieBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private fun showMenu(v: View, movie: Movie) {
        PopupMenu(v.context, v).apply {
            menuInflater.inflate(menuRes, menu)

            setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.delete_movie_shopping -> {
                        actionShoppingCart(false, movie.id)
                        true
                    }
                    R.id.detail_movie_shopping -> {
                        navigate(movie, v)
                        true
                    }
                    else -> false
                }
            }
        }.also { it.show() }
    }

    private fun navigate(elem: Movie, view: View) {
        view.findNavController().navigate(
                R.id.action_shoppingCartFragment_to_aboutOfMovieFragment, bundleOf(
                "movie" to elem
        )
        )
    }

    fun bind(item: Movie) {
        binding.apply {
            itemImage.loadImage(item.posterPath)
            itemName.text = item.title
            itemStadium.text = item.popularity.toString()
            cardMovie.setOnClickListener { v ->
                showMenu(v, item)
            }
        }
    }
}