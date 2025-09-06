package com.sejun2.trippath.presentation.util

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object TripPathConstants {
    val APP_BAR_HEIGHT: Dp = 56.dp
}

fun Modifier.tripPathDefaultContentPadding() = this.padding(horizontal = 16.dp)