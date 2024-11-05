package com.kino.movies.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.domain.usecase.movie.GetFavoriteMoviesUseCase
import com.kino.movies.presentation.utils.UiNotification
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _notification = MutableSharedFlow<UiNotification>()
    val notification = _notification.asSharedFlow()

    private val _viewState = MutableStateFlow<FavoriteViewState?>(null)
    val viewState = _viewState.onStart {
        getFavoriteMovies()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = FavoriteViewState.Loading
    )

    fun getFavoriteMovies() {
        // TODO : get favorite movies

    }


}