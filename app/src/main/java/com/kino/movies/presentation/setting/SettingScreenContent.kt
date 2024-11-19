package com.kino.movies.presentation.setting

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.kino.movies.domain.model.AppLanguage
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.presentation.designsystem.component.CustomSwitch
import com.kino.movies.presentation.designsystem.component.KinoUiLoading

@Composable
fun SettingScreenContent(
    viewState: SettingViewState?, modifier: Modifier,
    onEvent: (SettingEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
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
    LanguageSetting(viewState.language, onEvent)
}

@Composable
fun themeSetting(
    item: SettingUiItem,
    onEvent: (SettingEvent) -> Unit
) {
    SettingItem(
        icon = item.icon,
        title = item.settingName,
        subTitle = item.subTitle,
        isSwitchUi = item.isSwitchUi,
        isSwitchUiChecked = item.isSwitchChecked,
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
    item: SettingUiItem,
    onEvent: (SettingEvent) -> Unit
) {
    SettingItem(
        icon = item.icon,
        title = item.settingName,
        subTitle = item.subTitle,
        onBoxClicked = {
            // TODO OPEN LANG CHOICE DIALOG
            val random = (0..6).random()
            onEvent(SettingEvent.OnChangeLanguage(AppLanguage.entries[random]))
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
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

            }
            Text(
                text = subTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = Color.LightGray
            )
            if (isSwitchUi) {
                var switchOn by remember {
                    mutableStateOf(isSwitchUiChecked)
                }
                CustomSwitch(
                    switchOn = switchOn,
                    onSwitch = { switched ->
                        switchOn = switched
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