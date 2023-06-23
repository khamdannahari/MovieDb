package com.androidindonesia.moviedb.framework.presentation.feature.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidindonesia.moviedb.business.model.GenresItemModel
import com.androidindonesia.moviedb.business.model.MovieItemModel
import com.androidindonesia.moviedb.business.usecase.abstraction.MovieUseCase
import com.androidindonesia.moviedb.framework.presentation.util.PaginationDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    private val _moviesDataState: MutableLiveData<PaginationDataState<List<MovieItemModel>>> =
        MutableLiveData()
    val moviesDataState: LiveData<PaginationDataState<List<MovieItemModel>>> = _moviesDataState

    private val _genres: MutableLiveData<List<GenresItemModel>> = MutableLiveData()
    val genres: List<GenresItemModel> = _genres.value.orEmpty()

    init {
        getGenres()
        getMovies(FIRST_PAGE)
    }

    private fun getGenres() {
        viewModelScope.launch {
            useCase.getGenres()
                .collect { _genres.value = it.genres }
        }
    }

    fun getMovies(page: Int = FIRST_PAGE) {
        viewModelScope.launch {
            val genresSelected = genres.filter { it.isSelected }.map { it.id }
            useCase.getMovies(page, genresSelected)
                .onStart {
                    if (page == FIRST_PAGE) {
                        _moviesDataState.value = PaginationDataState.FirstLoading
                    } else {
                        _moviesDataState.value = PaginationDataState.PaginationLoading
                    }
                }
                .catch { _moviesDataState.value = PaginationDataState.Failure(it) }
                .collect {
                    when (it.page) {
                        it.totalPages ->
                            _moviesDataState.value = PaginationDataState.PaginationEnded
                        FIRST_PAGE -> _moviesDataState.value = PaginationDataState.First(it.results)
                        else -> _moviesDataState.value = PaginationDataState.Append(it.results)
                    }
                }
        }
    }

    fun updateGenres(genre: GenresItemModel) {
        _genres.value = _genres.value?.map {
            if (it.id == genre.id) genre else it
        }.orEmpty()
    }

    private companion object {
        const val FIRST_PAGE = 1
    }
}
