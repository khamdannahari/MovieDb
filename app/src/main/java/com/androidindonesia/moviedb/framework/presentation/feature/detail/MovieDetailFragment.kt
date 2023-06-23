package com.androidindonesia.moviedb.framework.presentation.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidindonesia.moviedb.databinding.FragmentMovieDetailBinding
import com.androidindonesia.moviedb.framework.presentation.util.DataState
import com.androidindonesia.moviedb.framework.presentation.util.PaginationController
import com.androidindonesia.moviedb.framework.presentation.util.PaginationDataState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailVewModel by viewModels()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()

    private val paginationController by lazy {
        PaginationController()
    }

    private val reviewsAdapter by lazy {
        ReviewsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDetails(args.id)
        viewModel.getReviews(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackFab()
        observeDetails()
        observeReviews()
        setupReviewsRv()
    }

    private fun setBackFab() {
        binding.backFab.setOnClickListener { findNavController().navigateUp() }
    }

    private fun observeDetails() {
        viewModel.detailDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.loadingPb.isVisible = true
                    binding.errorTv.isVisible = false
                }
                is DataState.Success -> {
                    binding.loadingPb.isVisible = false
                    binding.errorTv.isVisible = false
                    binding.titleTv.text = it.data.first.title
                    binding.descTv.text = it.data.first.overview

                    val videoId = it.data.second.results.find { it.isYoutubeSite }?.key.orEmpty()
                    binding.trailerFl.initYouTubePlayer(videoId)
                }
                is DataState.Failure -> {
                    binding.loadingPb.isVisible = false
                    binding.errorTv.isVisible = true
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun observeReviews() {
        viewModel.reviewsDataState.observe(viewLifecycleOwner) {
            when (it) {
                is PaginationDataState.First -> {
                    paginationController.resetPageNumber()
                    paginationController.isLastPage(false)
                    reviewsAdapter.submitList(it.data)
                }
                is PaginationDataState.Append -> {
                    reviewsAdapter.submitList(reviewsAdapter.currentList.plus(it.data))
                }
                is PaginationDataState.Failure -> {
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
                PaginationDataState.FirstLoading -> Unit
                PaginationDataState.PaginationEnded -> paginationController.isLastPage(true)
                PaginationDataState.PaginationLoading -> paginationController.isLoading(true)
            }

            binding.reviewsRv.isVisible = reviewsAdapter.itemCount > 0
            binding.noReviewsTv.isVisible = reviewsAdapter.itemCount <= 0
        }
    }

    private fun setupReviewsRv() = with(binding.reviewsRv) {
        adapter = reviewsAdapter
        layoutManager.apply {
            if (this is LinearLayoutManager) {
                paginationController.setLayoutManager(this)
                binding.reviewsRv.addOnScrollListener(paginationController)
                paginationController.onLoadMoreItems { page ->
                    viewModel.getReviews(args.id, page)
                }
            }
        }
    }

    private fun FrameLayout.initYouTubePlayer(id: String) {
        val youTubePlayerView = YouTubePlayerView(requireContext()).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        this.addView(youTubePlayerView)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0F)
            }
        })
    }

    override fun onDestroyView() {
        paginationController.clean()
        binding.reviewsRv.adapter = null
        binding.reviewsRv.layoutManager = null
        super.onDestroyView()
        _binding = null
    }

}

