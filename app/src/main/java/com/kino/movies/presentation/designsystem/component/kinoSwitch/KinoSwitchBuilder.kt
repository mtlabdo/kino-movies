package com.kino.movies.presentation.designsystem.component.kinoSwitch

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val CHECKED_TRACK_COLOR = Color(0xFF35898F)
private val UNCHECKED_TRACK_COLOR = Color(0xFFe0e0e0)

class KinoSwitchBuilder {
    private var width: Dp = 45.dp
    private var height: Dp = 30.dp
    private var checkedTrackColor: Color = CHECKED_TRACK_COLOR
    private var uncheckedTrackColor: Color = UNCHECKED_TRACK_COLOR
    private var gapBetweenThumbAndTrackEdge: Dp = 4.dp
    private var borderWidth: Dp = 2.dp
    private var cornerSize: Int = 50
    private var iconInnerPadding: Dp = 4.dp
    private var thumbSize: Dp = 20.dp

    fun width(value: Dp) = apply { this.width = value }
    fun height(value: Dp) = apply { this.height = value }
    fun checkedTrackColor(value: Color) = apply { this.checkedTrackColor = value }
    fun uncheckedTrackColor(value: Color) = apply { this.uncheckedTrackColor = value }
    fun gapBetweenThumbAndTrackEdge(value: Dp) = apply { this.gapBetweenThumbAndTrackEdge = value }
    fun borderWidth(value: Dp) = apply { this.borderWidth = value }
    fun cornerSize(value: Int) = apply { this.cornerSize = value }
    fun iconInnerPadding(value: Dp) = apply { this.iconInnerPadding = value }
    fun thumbSize(value: Dp) = apply { this.thumbSize = value }

    fun build(): KinoSwitchParams {
        return KinoSwitchParams(
            width, height, checkedTrackColor, uncheckedTrackColor,
            gapBetweenThumbAndTrackEdge, borderWidth, cornerSize,
            iconInnerPadding, thumbSize
        )
    }

    val default = this.build()
}