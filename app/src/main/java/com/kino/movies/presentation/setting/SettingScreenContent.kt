package com.kino.movies.presentation.setting

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.presentation.designsystem.component.CustomSwitch
import com.kino.movies.presentation.designsystem.component.KinoUiLoading
import com.kino.movies.presentation.designsystem.composable.SpacerVertical16

@Composable
fun SettingScreenContent(
    viewState: SettingViewState?, modifier: Modifier,
    onEvent: (SettingEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        when (viewState) {
            is SettingViewState.Loading -> {
                KinoUiLoading()
            }

            is SettingViewState.Setting -> {
                SettingList(viewState, onEvent)
            }

            else -> Unit
        }
    }
}


@Composable
fun SettingList(
    viewState: SettingViewState.Setting,
    onEvent: (SettingEvent) -> Unit
) {
    themeSetting(viewState.theme, onEvent)
    SpacerVertical16()
    LanguageSetting(viewState.language, onEvent)
}

@Composable
fun themeSetting(
    themeState: ThemeSetting,
    onEvent: (SettingEvent) -> Unit
) {
    SettingItem(
        icon = themeState.settingUiItem.icon,
        title = themeState.settingUiItem.settingName,
        isSwitchUi = themeState.settingUiItem.isSwitchUi,
        isSwitchUiChecked = themeState.isDarkTheme,
        onSwitch = { switchedOn ->
            if (switchedOn) {
                onEvent(SettingEvent.OnChangeTheme(AppTheme.DARK_THEME))
            } else
                onEvent(SettingEvent.OnChangeTheme(AppTheme.LIGHT_THEME))
        },
    )
}

@Composable
fun LanguageSetting(
    languageState: LanguageSetting,
    onEvent: (SettingEvent) -> Unit
) {
    var isDialogOpen by remember { mutableStateOf(false) }
    if (isDialogOpen) {
        LanguagePickerDialog(
            languages = languageState.languages,
            selectedLanguage = languageState.selectedLanguage,
            onLanguageSelected = { selectedLanguage ->
                onEvent(
                    SettingEvent.OnChangeLanguage(selectedLanguage)
                )
                isDialogOpen = false
            },
            onDismiss = { isDialogOpen = false }
        )
    }
    SettingItem(
        icon = languageState.settingUiItem.icon,
        title = languageState.settingUiItem.settingName,
        subTitle = languageState.settingUiItem.subTitle,
        onBoxClicked = {
            isDialogOpen = true
        }

    )
}


@Composable
fun SettingItem(
    @DrawableRes icon: Int,
    title: String,
    subTitle: String = "",
    isSwitchUi: Boolean = false,
    isSwitchUiChecked: Boolean = false,
    onSwitch: ((Boolean) -> Unit)? = null,
    onBoxClicked: (() -> Unit) = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onBoxClicked()
            },
        shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(shape = CircleShape),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(40.dp)
                )
            }

            Box(
                contentAlignment = Alignment.CenterStart, modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

            }
            Text(
                text = subTitle,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = MaterialTheme.colorScheme.outline
            )
            if (isSwitchUi) {
                CustomSwitch(
                    switchOn = isSwitchUiChecked,
                    onSwitch = { switched ->
                        onSwitch?.invoke(switched)
                    })
            } else DefaultBox(iconSize = 25)
        }
    }
}

@Composable
fun DefaultBox(iconSize: Int) {
    Box(
        contentAlignment = Alignment.CenterEnd, modifier = Modifier.clip(RoundedCornerShape(16.dp))
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(iconSize.dp)
        )
    }
}