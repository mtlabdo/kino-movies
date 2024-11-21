package com.kino.movies.presentation.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kino.movies.domain.model.AppLanguage

@Composable
fun SettingScreen(
    settingViewState: SettingViewState?,
    modifier: Modifier,
    onEvent: (SettingEvent) -> Unit
) {
    SettingScreenContent(
        viewState = settingViewState,
        modifier = modifier,
        onEvent = onEvent
    )
}
/*


@Preview
@Composable
fun SettingScreenPreview() {
    SettingScreen(
        settingViewState = SettingViewState.Setting(
            language = LanguageSetting(
                settingUiItem = languageSetting.apply {
                    subTitle = languagesList.first().second
                },
                selectedLanguage = AppLanguage.FRENCH,
                languages = languagesList
            ),
            theme = ThemeSetting(
                settingUiItem = darkModeSetting,
                isDarkTheme = true
            )
        ),
        modifier = Modifier,
        onEvent = {}
    )
}*/
