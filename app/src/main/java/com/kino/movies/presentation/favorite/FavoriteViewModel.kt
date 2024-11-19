package com.kino.movies.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.domain.usecase.movie.GetFavoriteMoviesUseCase
import com.kino.movies.presentation.utils.UiNotification
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.kino.movies.domain.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class FavoriteViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _notification = Channel<UiNotification>()
    val notification = _notification.receiveAsFlow()

    private val _viewState = MutableStateFlow<FavoriteViewState?>(null)
    val viewState = _viewState.onStart {
        getFavoriteMovies()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = FavoriteViewState.Loading
    )

    fun getFavoriteMovies() {
        viewModelScope.launch(ioDispatcher) {
            getFavoriteMoviesUseCase().collect { result ->

                when (result) {
                    is Result.Success -> {
                        _viewState.value = FavoriteViewState.FavoriteMoviesReady(result.value)
                    }

                    is Result.Error -> {
                        _viewState.value = null
                        val errorNotification = UiNotification.Error(
                            title = "Oups!",
                            message = "Erreur lors de la récupération des films favoris, veuillez réessayer !"
                        )
                        _notification.send(errorNotification)
                    }
                }
            }
        }
    }
}