package com.sejun2.trippath.presentation.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object TripPathConstants {
    val APP_BAR_HEIGHT: Dp = 56.dp
}

fun Modifier.tripPathDefaultContentPadding() = this.padding(horizontal = 16.dp)

fun Modifier.circleClickable(enabled: Boolean = true,onClick: () -> Unit) =
    this
        .clip(shape = CircleShape)
        .clickable(
            enabled = enabled,
            onClick = onClick,
        )
        .padding(8.dp)
