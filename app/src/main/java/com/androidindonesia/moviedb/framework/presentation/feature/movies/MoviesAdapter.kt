package com.androidindonesia.moviedb.framework.presentation.feature.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidindonesia.moviedb.business.model.MovieItemModel
import com.androidindonesia.moviedb.databinding.ItemMovieBinding
import com.androidindonesia.moviedb.framework.presentation.util.loadMovieImage

class MoviesAdapter(
    val onClick: (MovieItemModel) -> Unit = {}
) : ListAdapter<MovieItemModel, MoviesAdapter.MovieHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    inner class MovieHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItemModel) {
            binding.photoIv.loadMovieImage(movie.posterPath)
            binding.titleTv.text = movie.title
            binding.root.setOnClickListener {
                onClick.invoke(movie)
            }
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieItemModel>() {
        override fun areItemsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel) =
            oldItem == newItem
    }

}
