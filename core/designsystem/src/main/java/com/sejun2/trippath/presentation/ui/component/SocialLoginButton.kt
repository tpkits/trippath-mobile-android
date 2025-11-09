package com.sejun2.trippath.presentation.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTypography

@Composable
fun SocialLoginButton(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color,
    borderColor: Color? = null,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .then(
                if (borderColor != null) {
                    Modifier.border(
                        1.dp,
                        borderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                } else {
                    Modifier
                }
            )
            .padding(
                vertical = 12.dp,
                horizontal = 8.dp
            ),
    ) {
        Image(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .padding(start = 16.dp),
            painter = painterResource(iconRes),
            contentDescription = label
        )
        Text(
            modifier = Modifier.align(alignment = Alignment.Center),
            text = label,
            style = TripPathTypography.LabelMedium.copy(
                color = TripPathColors.Neutral10
            )
        )
    }
}
