package com.androidindonesia.moviedb.framework.presentation.feature.detail

import androidx.lifecycle.*
import com.androidindonesia.moviedb.business.model.MovieDetailModel
import com.androidindonesia.moviedb.business.model.VideoModel
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

    private val _videoDataState: MutableLiveData<DataState<VideoModel>> = MutableLiveData()
    val videoDataState: LiveData<DataState<VideoModel>> = _videoDataState

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            useCase.getMovieDetail(id)
                .onStart { _movieDetailDataState.value = DataState.Loading }
                .catch { _movieDetailDataState.value = DataState.Failure(it) }
                .collect {
                    _movieDetailDataState.value = DataState.Success(it)
                }

            useCase.getVideo(id)
                .onStart { _videoDataState.value = DataState.Loading }
                .catch { _videoDataState.value = DataState.Failure(it) }
                .collect { _videoDataState.value = DataState.Success(it) }
        }
    }
}
