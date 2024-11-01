package com.kino.movies.presentation.designsystem.components

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.kino.movies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KinoTopBar(
    context: Context
) {
    TopAppBar(
        title = {
            Text(
                text = context.resources.getString(R.string.home)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}