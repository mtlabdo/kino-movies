package com.kino.movies.presentation.home

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreenContent(
    state: HomeViewState,
    onEvent: () -> Unit,
    toDetail: (String) -> Unit,
    context: Context,
    modifier: Modifier
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = state.toString())
        Button(onClick = {
            toDetail("1")
        }) {
            Text(text = "Go to Detail")
        }
    }

}


