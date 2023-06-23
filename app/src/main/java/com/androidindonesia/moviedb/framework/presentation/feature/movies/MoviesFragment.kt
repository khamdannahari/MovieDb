package com.androidindonesia.moviedb.framework.presentation.feature.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidindonesia.moviedb.business.model.MovieItemModel
import com.androidindonesia.moviedb.databinding.FragmentMoviesBinding
import com.androidindonesia.moviedb.framework.presentation.util.InternetConnection.isInternetConnected
import com.androidindonesia.moviedb.framework.presentation.util.PaginationController
import com.androidindonesia.moviedb.framework.presentation.util.PaginationDataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by activityViewModels()

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val paginationController by lazy {
        PaginationController()
    }

    private val moviesAdapter by lazy {
        MoviesAdapter(::onClickMovieItem)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isInternetConnected(requireContext()).not()) {
            Toast.makeText(requireContext(), NO_INTERNET_MSG, Toast.LENGTH_SHORT).show()
            return
        }

        setupSwipeRefreshListener()
        setupFilterIv()
        setupMovieRv()
        observeMovies()
    }

    private fun onClickMovieItem(movie: MovieItemModel) {
        findNavController().navigate(MoviesFragmentDirections.openMovieDetail(movie.id))
    }

    private fun setupSwipeRefreshListener() {
        binding.moviesFragmentLayout.setOnRefreshListener { paginationController.reload() }
    }

    private fun setupFilterIv() {
        binding.filterIv.setOnClickListener {
            findNavController().navigate(
                MoviesFragmentDirections.openGenreDialog()
            )
        }
    }

    private fun setupMovieRv() = with(binding.movieRv) {
        adapter = moviesAdapter
        layoutManager.apply {
            if (this is LinearLayoutManager) {
                paginationController.setLayoutManager(this)
                binding.movieRv.addOnScrollListener(paginationController)
                paginationController.onLoadMoreItems { page ->
                    viewModel.getMovies(page)
                }
            }
        }
    }

    private fun observeMovies() {
        viewModel.moviesDataState.observe(viewLifecycleOwner) {
            when (it) {
                is PaginationDataState.First -> {
                    hideLoading()
                    paginationController.resetPageNumber()
                    paginationController.isLastPage(false)
                    moviesAdapter.submitList(it.data)
                }
                is PaginationDataState.Append -> {
                    hideLoading()
                    moviesAdapter.submitList(moviesAdapter.currentList.plus(it.data))
                }
                is PaginationDataState.Failure -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
                PaginationDataState.FirstLoading -> binding.moviesFragmentLayout.isRefreshing = true
                PaginationDataState.PaginationEnded -> paginationController.isLastPage(true)
                PaginationDataState.PaginationLoading -> paginationController.isLoading(true)
            }

            binding.errorTv.isVisible = moviesAdapter.itemCount <= 0
        }
    }

    private fun hideLoading() {
        binding.moviesFragmentLayout.isRefreshing = false
        paginationController.isLoading(false)
    }

    override fun onDestroyView() {
        paginationController.clean()
        binding.movieRv.adapter = null
        binding.movieRv.layoutManager = null
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val NO_INTERNET_MSG = "No internet connection"
    }
}

