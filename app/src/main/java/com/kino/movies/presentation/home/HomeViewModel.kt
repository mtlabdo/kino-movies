package com.kino.movies.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.domain.usecase.movie.GetMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state = _state.asStateFlow().onStart {
        searchMovies()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeState.Loading)


    init {

        viewModelScope.launch(ioDispatcher) {
           _state.collect {
               Log.d("HomeViewModel", "state: $it")
           }

        }
    }

    fun searchMovies(query: String? = null) {
        viewModelScope.launch(ioDispatcher) {
            getMoviesUseCase(query).catch {
                _state.value = HomeState.Error(it.message ?: "Erreur inconnue")
            }.collect {
                if (it.isSuccess) {
                    _state.value = HomeState.Success(it.getOrNull() ?: emptyList())
                }
                if (it.isFailure) {
                    _state.value =
                        HomeState.Error(it.exceptionOrNull()?.message ?: "Erreur inconnue")
                }
            }
        }
    }
}