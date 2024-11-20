package com.kino.movies.presentation.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kino.movies.presentation.utils.BottomBarDestination

@Composable
fun KinoBottomBar(
    destinations: List<BottomBarDestination>,
    currentRoute: String?,
    onNavigateToDestination: (BottomBarDestination) -> Unit = {}
) {
    val context = LocalContext.current
    NavigationBar {
        destinations.forEach { destination ->
            val selected = destination.route == currentRoute
            val title = context.resources.getString(destination.title)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = title,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(MaterialTheme.shapes.small)
                    )
                },
                label = {
                    Text(title)
                },
            )
        }

    }
}

