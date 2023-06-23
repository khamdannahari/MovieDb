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
import androidx.navigation.fragment.navArgs
import com.androidindonesia.moviedb.databinding.FragmentMovieDetailBinding
import com.androidindonesia.moviedb.framework.presentation.util.DataState
import com.androidindonesia.moviedb.framework.presentation.util.loadMovieImage
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieDetail(args.id)
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
        observeMovieDetail()
    }

    private fun observeMovieDetail() {
        viewModel.movieDetailDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.loadingPb.isVisible = true
                }
                is DataState.Success -> {
                    binding.loadingPb.isVisible = false
                    binding.photoIv.loadMovieImage(it.data.posterPath)
                    binding.titleTv.text = it.data.title
                    binding.descTv.text = it.data.overview
                }
                is DataState.Failure -> {
                    binding.loadingPb.isVisible = false
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        viewModel.videoDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> Unit
                is DataState.Success -> {
                    binding.trailerFl.initializeYouTubePlayer(
                        it.data.results.find { it.isYoutubeSite }?.key.orEmpty()
                    )
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun FrameLayout.initializeYouTubePlayer(id: String) {
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
        super.onDestroyView()
        _binding = null
    }

}

