package com.kino.movies.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.R
import com.kino.movies.domain.Result
import com.kino.movies.domain.usecase.movie.GetMoviesUseCase
import com.kino.movies.presentation.utils.UIText
import com.kino.movies.presentation.utils.UiNotification
import com.kino.movies.presentation.utils.UiNotificationController
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _viewState = MutableStateFlow<HomeViewState?>(HomeViewState.Loading)
    val viewState = _viewState.onStart {
        searchMovies()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = HomeViewState.Loading
    )

    fun searchMovies(query: String? = null) {
        viewModelScope.launch(ioDispatcher) {
            getMoviesUseCase(query).onStart {
                _viewState.value = HomeViewState.Loading
            }.collect { result ->
                when (result) {
                    is Result.Success -> {
                        _viewState.value = HomeViewState.MoviesReady(result.value)
                    }

                    is Result.Error -> {
                        _viewState.value = null
                        val errorNotification = UiNotification.SnackBarNotificationEvent(
                            message = result.message
                                ?: UIText.StringResource(R.string.error_get_movies),
                            actionLabel = UIText.StringResource(R.string.retry),
                            action = { searchMovies() },
                        )
                        UiNotificationController.sendUiNotification(errorNotification)
                    }
                }
            }
        }
    }
}