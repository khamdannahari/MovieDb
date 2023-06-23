package com.androidindonesia.moviedb.framework.presentation.feature.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.androidindonesia.moviedb.databinding.DialogGenresBinding
import com.androidindonesia.moviedb.framework.presentation.feature.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresDialogFragment : DialogFragment() {

    private val viewModel: MoviesViewModel by activityViewModels()

    private var _binding: DialogGenresBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogGenresBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        setupGenreRv()
        setupOkButton()
    }

    private fun setupGenreRv() {
        binding.genreRv.adapter = GenreAdapter(viewModel.genres) {
            viewModel.updateGenres(it)
        }
    }

    private fun setupOkButton() {
        binding.okBtn.setOnClickListener {
            viewModel.getMovies()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

