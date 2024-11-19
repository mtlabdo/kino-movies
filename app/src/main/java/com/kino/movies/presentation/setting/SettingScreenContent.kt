package com.kino.movies.presentation.setting

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

            is SettingViewState.SettingItems -> {
                SettingList(viewState.settings, onEvent)
            }

            else -> Unit
        }
    }
}


@Composable
fun SettingList(
    settingItems: List<SettingUiItem>,
    onEvent: (SettingEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            settingItems,
        ) {
            SettingItem(it, onEvent)
        }
    }
}


@Composable
fun SettingItem(
    item: SettingUiItem,
    onEvent: (SettingEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                when (item.id) {
                    "dark_mode" -> {
                        onEvent(SettingEvent.OnChangeTheme(AppTheme.LIGHT_THEME))
                    }
                    "language" -> onEvent(SettingEvent.OnChangeLanguage(AppLanguage.FRENCH))
                }
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
                    painter = painterResource(id = item.icon),
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
                    text = item.settingName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

            }
            Text(
                text = item.subTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = Color.LightGray
            )
            if (item.isSwitchUi) {
                CustomSwitch()
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