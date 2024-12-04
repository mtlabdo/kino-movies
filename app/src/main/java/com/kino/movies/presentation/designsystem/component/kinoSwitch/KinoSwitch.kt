package com.kino.movies.presentation.designsystem.component.kinoSwitch

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.kino.movies.R
import com.kino.movies.presentation.designsystem.composable.SpacerHorizontal16
import com.kino.movies.presentation.designsystem.composable.SpacerVertical16

val defaultSwitchParams = KinoSwitchBuilder().build()
const val SWITCH_ANIMATION_DURATION = 500

@Composable
fun CustomSwitch(
    switchParams: KinoSwitchParams = defaultSwitchParams,
    switchOn: Boolean,
    onSwitch: ((Boolean) -> Unit)? = null
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val alignment by animateAlignmentAsState(if (switchOn) 1f else -1f)

    Box(
        modifier = Modifier

            .animateContentSize(
                animationSpec = tween(SWITCH_ANIMATION_DURATION)
            ), contentAlignment = alignment
    ) {
        Text(
            text = if (switchOn) stringResource(R.string.on) else "       ${stringResource(R.string.off)}",
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = MaterialTheme.colorScheme.outline
        )
    }
    SpacerHorizontal16()
    Box(
        modifier = Modifier
            .size(width = switchParams.width, height = switchParams.height)
            .border(
                width = switchParams.borderWidth,
                color = if (switchOn) switchParams.checkedTrackColor else switchParams.uncheckedTrackColor,
                shape = RoundedCornerShape(percent = switchParams.cornerSize)
            )
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                onSwitch?.invoke(!switchOn)
            },
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .padding(
                    start = switchParams.gapBetweenThumbAndTrackEdge,
                    end = switchParams.gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment
        ) {
            Icon(
                imageVector = if (switchOn) Icons.Filled.Done else Icons.Filled.Close,
                contentDescription = if (switchOn) "Enabled" else "Disabled",
                modifier = Modifier
                    .size(size = switchParams.thumbSize)
                    .background(
                        color = if (switchOn) switchParams.checkedTrackColor else switchParams.uncheckedTrackColor,
                        shape = CircleShape
                    )
                    .padding(all = switchParams.iconInnerPadding),
                tint = Color.White
            )
        }
    }
    SpacerVertical16()
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue, label = "")
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}
