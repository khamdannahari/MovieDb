package com.androidindonesia.moviedb.framework.presentation.feature.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidindonesia.moviedb.business.model.GenresItemModel
import com.androidindonesia.moviedb.databinding.ItemGenreBinding

class GenreAdapter(
    private val items: List<GenresItemModel>,
    private val onCheck: (GenresItemModel) -> Unit = {}
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GenresItemModel) {
            binding.genreTv.text = item.name
            binding.checkCb.isChecked = item.isSelected
            binding.checkCb.setOnCheckedChangeListener { _, checked ->
                onCheck.invoke(item.copy(isSelected = checked))
            }
        }
    }
}
