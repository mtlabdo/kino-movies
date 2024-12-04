package com.kino.movies.presentation.setting

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.presentation.designsystem.component.kinoSwitch.CustomSwitch
import com.kino.movies.presentation.designsystem.component.kinoSwitch.KinoSwitchBuilder
import com.kino.movies.presentation.designsystem.component.KinoUiLoading
import com.kino.movies.presentation.designsystem.composable.SpacerVertical16
import java.util.Locale

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
@ReadOnlyComposable
fun getLocale(): Locale {
    val configuration = LocalConfiguration.current
    return ConfigurationCompat.getLocales(configuration).get(0) ?: LocaleListCompat.getDefault()[0]!!
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
        title = stringResource(id = themeState.settingUiItem.title),
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
    val context = LocalContext.current
    var isDialogOpen by remember { mutableStateOf(false) }
    val selectedLanguageCode = getLocale().language

    if (isDialogOpen) {
        LanguagePickerDialog(
            selectedLanguageCode = selectedLanguageCode,
            onLanguageSelected = { selectedLanguage ->
                onEvent(
                    SettingEvent.OnChangeLanguage(selectedLanguage)
                )
                isDialogOpen = false
                Language.setLocale(context = context, localeCode = selectedLanguage.code)
            },
            onDismiss = { isDialogOpen = false }
        )
    }
    SettingItem(
        icon = languageState.settingUiItem.icon,
        title = stringResource(id = languageState.settingUiItem.title),
        subTitle = if(Language.getLanguageByCode(selectedLanguageCode)?.titleRes != null) {
            stringResource(id = Language.getLanguageByCode(selectedLanguageCode)!!.titleRes)
        } else {
            selectedLanguageCode
        },
        onBoxClicked = {
            isDialogOpen = true
        }
    )
}

@Composable
fun SettingItem(
    @DrawableRes icon: Int,
    title: String,
    subTitle: String? = "",
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
        Row (
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
                        .size(32.dp)
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
                text = subTitle ?: "",
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = MaterialTheme.colorScheme.outline
            )
            if (isSwitchUi) {
                val uncheckedTrackColor = MaterialTheme.colorScheme.outline
                val customSwitchParams = remember(uncheckedTrackColor) {
                    KinoSwitchBuilder().uncheckedTrackColor(uncheckedTrackColor).build()
                }
                CustomSwitch(
                    switchParams = customSwitchParams,
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


