package com.sejun2.trippath.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sejun2.trippath.core.designsystem.R
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import com.sejun2.trippath.presentation.util.TripPathConstants

@Composable
fun TripPathMainAppBar(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = 12.dp,
    ),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    title: @Composable () -> Unit = {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(
                    width = 28.dp,
                    height = 28.dp
                ),
                painter = painterResource(R.drawable.ic_logo_trippath),
                contentDescription = "image_ic_logo_trippath",

                )
            Text(
                "TRIP PATH", style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 28.sp
                )
            )
        }
    },
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .height(TripPathConstants.APP_BAR_HEIGHT)
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Row {
            navigationIcon?.let { navigationIcon ->
                navigationIcon()
                Spacer(
                    modifier = Modifier.weight(1f)
                )
            }
            title()
            Spacer(
                modifier = Modifier.weight(1f)
            )
            actions?.invoke()
        }
    }
}

@Preview()
@Composable
fun TripPathMainAppBarPreview() {
    TripPathTheme {
        TripPathMainAppBar { }
    }
}
