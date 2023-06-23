package com.androidindonesia.moviedb.framework.presentation.feature.detail

import androidx.lifecycle.*
import com.androidindonesia.moviedb.business.model.*
import com.androidindonesia.moviedb.business.usecase.abstraction.MovieUseCase
import com.androidindonesia.moviedb.framework.presentation.util.DataState
import com.androidindonesia.moviedb.framework.presentation.util.PaginationDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailVewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private val _detailDataState: MutableLiveData<DataState<Pair<MovieDetailModel, VideosModel>>> =
        MutableLiveData()
    val detailDataState: LiveData<DataState<Pair<MovieDetailModel, VideosModel>>> =
        _detailDataState

    private val _reviewsDataState: MutableLiveData<PaginationDataState<List<ReviewResultModel>>> =
        MutableLiveData()
    val reviewsDataState: LiveData<PaginationDataState<List<ReviewResultModel>>> = _reviewsDataState

    fun getDetails(id: Int) {
        viewModelScope.launch {
            combine(
                useCase.getMovieDetail(id),
                useCase.getVideos(id)
            ) { movieDetail, videos ->
                Pair(movieDetail, videos)
            }.onStart { _detailDataState.value = DataState.Loading }
                .catch { _detailDataState.value = DataState.Failure(it) }
                .collect { _detailDataState.value = DataState.Success(it) }

        }
    }

    fun getReviews(id: Int, page: Int = FIRST_PAGE) {
        viewModelScope.launch {
            useCase.getReviews(id, page)
                .onStart {
                    if (page == FIRST_PAGE) {
                        _reviewsDataState.value = PaginationDataState.FirstLoading
                    } else {
                        _reviewsDataState.value = PaginationDataState.PaginationLoading
                    }
                }
                .catch { _reviewsDataState.value = PaginationDataState.Failure(it) }
                .collect {
                    when (it.page) {
                        it.totalPages.inc() ->
                            _reviewsDataState.value = PaginationDataState.PaginationEnded
                        FIRST_PAGE ->
                            _reviewsDataState.value = PaginationDataState.First(it.results)
                        else ->
                            _reviewsDataState.value = PaginationDataState.Append(it.results)
                    }
                }
        }
    }

    private companion object {
        const val FIRST_PAGE = 1
    }
}
