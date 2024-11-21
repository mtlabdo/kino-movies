package com.kino.movies.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.R
import com.kino.movies.domain.usecase.movie.GetMovieDetailUseCase
import com.kino.movies.presentation.utils.UiNotification
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.kino.movies.domain.Result
import com.kino.movies.domain.usecase.movie.AddFavoriteMovieUseCase
import com.kino.movies.domain.usecase.movie.DeleteFavoriteMovieUseCase
import com.kino.movies.presentation.utils.UIText
import com.kino.movies.presentation.utils.UiNotificationController

class DetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow<DetailViewState?>(null)
    val viewState = _viewState.asStateFlow()

    fun getMovieDetail(idMovie: String) {
        viewModelScope.launch(ioDispatcher) {
            _viewState.value = DetailViewState.Loading
            getMovieDetailUseCase.invoke(idMovie).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _viewState.value = DetailViewState.MovieDetailReady(result.value)
                    }

                    is Result.Error -> {
                        _viewState.value = null
                        val errorNotification = UiNotification.SnackBarNotificationEvent(
                            message = result.message
                                ?: UIText.StringResource(R.string.error_get_movie_detail),
                        )
                        UiNotificationController.sendUiNotification(errorNotification)
                    }
                }
            }
        }
    }

    fun updateFavoriteOfMovie(movieId: String, isFavorite: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            if (isFavorite) {
                addFavoriteMovieUseCase.invoke(movieId)
            } else {
                deleteFavoriteMovieUseCase.invoke(movieId)
            }
        }
    }
}