package com.kino.movies.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.domain.Result
import com.kino.movies.domain.usecase.movie.GetMoviesUseCase
import com.kino.movies.presentation.utils.UiNotification
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _notification = MutableSharedFlow<UiNotification>()
    val notification = _notification.asSharedFlow()

    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val viewState = _viewState.onStart {
        searchMovies()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = HomeViewState.Loading
    )

    fun searchMovies(query: String? = null) {
        Log.e("MovieNetworkDataSource", "searchMovies: $query")
        viewModelScope.launch(ioDispatcher) {
            getMoviesUseCase(query).collect { result ->
                Log.e("MovieNetworkDataSource", "searchMovies res: $result")
                when (result) {
                    is Result.Success -> {
                        _viewState.value = HomeViewState.MoviesReady(result.value)
                    }

                    is Result.Error -> {
                        val errorNotification = UiNotification.Error(
                            title = "Oups, Erreur ${result.code}",
                            message = "${result.message}"
                        )
                        _notification.emit(errorNotification)
                    }
                }
            }
        }
    }
}