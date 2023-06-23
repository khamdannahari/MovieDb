package com.androidindonesia.moviedb.framework.presentation.feature.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidindonesia.moviedb.business.model.ReviewResultModel
import com.androidindonesia.moviedb.databinding.ItemReviewBinding

class ReviewsAdapter :
    ListAdapter<ReviewResultModel, ReviewsAdapter.MovieHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    inner class MovieHolder(
        private val binding: ItemReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: ReviewResultModel) {
            binding.nameTv.text = movie.author
            binding.reviewTv.text = movie.content
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<ReviewResultModel>() {
        override fun areItemsTheSame(oldItem: ReviewResultModel, newItem: ReviewResultModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ReviewResultModel, newItem: ReviewResultModel) =
            oldItem == newItem
    }

}
