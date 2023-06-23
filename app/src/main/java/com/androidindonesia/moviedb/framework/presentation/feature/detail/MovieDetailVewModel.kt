package com.androidindonesia.moviedb.framework.presentation.feature.detail

import androidx.lifecycle.*
import com.androidindonesia.moviedb.business.model.MovieDetailModel
import com.androidindonesia.moviedb.business.model.ReviewsModel
import com.androidindonesia.moviedb.business.model.VideosModel
import com.androidindonesia.moviedb.business.usecase.abstraction.MovieUseCase
import com.androidindonesia.moviedb.framework.presentation.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailVewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private val _movieDetailDataState: MutableLiveData<DataState<MovieDetailModel>> =
        MutableLiveData()
    val movieDetailDataState: LiveData<DataState<MovieDetailModel>> = _movieDetailDataState

    private val _videosDataState: MutableLiveData<DataState<VideosModel>> = MutableLiveData()
    val videosDataState: LiveData<DataState<VideosModel>> = _videosDataState

    private val _reviewsDataState: MutableLiveData<DataState<ReviewsModel>> = MutableLiveData()
    val reviewsDataState: LiveData<DataState<ReviewsModel>> = _reviewsDataState

    fun getDetails(id: Int) {
        viewModelScope.launch {
            useCase.getMovieDetail(id)
                .onStart { _movieDetailDataState.value = DataState.Loading }
                .catch { _movieDetailDataState.value = DataState.Failure(it) }
                .collect {
                    _movieDetailDataState.value = DataState.Success(it)
                }

            useCase.getVideos(id)
                .onStart { _videosDataState.value = DataState.Loading }
                .catch { _videosDataState.value = DataState.Failure(it) }
                .collect { _videosDataState.value = DataState.Success(it) }

            useCase.getReviews(id)
                .onStart { _reviewsDataState.value = DataState.Loading }
                .catch { _reviewsDataState.value = DataState.Failure(it) }
                .collect { _reviewsDataState.value = DataState.Success(it) }
        }
    }
}
