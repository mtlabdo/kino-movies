package com.kino.movies.presentation.designsystem.component.kinoSwitch

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class KinoSwitchParams(
    val width: Dp,
    val height: Dp,
    val checkedTrackColor: Color,
    val uncheckedTrackColor: Color,
    val gapBetweenThumbAndTrackEdge: Dp ,
    val borderWidth: Dp,
    val cornerSize: Int,
    val iconInnerPadding: Dp,
    val thumbSize: Dp,
)