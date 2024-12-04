package com.kino.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.domain.usecase.apptheme.GetAppThemeUseCase
import com.kino.movies.presentation.designsystem.theme.KinomoviesTheme
import com.kino.movies.presentation.MainScreen
import com.kino.movies.presentation.utils.ObserveAsEvents
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()

            val getAppThemeUseCase = koinInject<GetAppThemeUseCase>()
            var isDarkTheme by remember { mutableStateOf<Boolean?>(null) }
            ObserveAsEvents(getAppThemeUseCase.invoke()) { theme ->
                scope.launch {
                    isDarkTheme = when (theme) {
                        AppTheme.DARK_THEME -> true
                        AppTheme.LIGHT_THEME -> false
                        AppTheme.FOLLOW_SYSTEM -> null
                    }
                }
            }
            KinomoviesTheme(isDarkTheme ?: isSystemInDarkTheme()) {
                MainScreen(navController)
            }
        }
    }
}
