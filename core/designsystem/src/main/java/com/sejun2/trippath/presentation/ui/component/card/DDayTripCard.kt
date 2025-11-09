package com.sejun2.trippath.presentation.ui.component.card

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.LocalAsyncImagePreviewHandler
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import com.sejun2.trippath.presentation.ui.theme.TripPathTypography

@Composable
fun DDayTripCard(
    modifier: Modifier = Modifier,
    imgUrl: String,
    title: String,
    dateInterval: String,
    city: String,
    onExtraButtonClick: (() -> Unit)?
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = TripPathColors.BorderDefault,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp)),
    ) {
        Row(
            modifier =
                Modifier.fillMaxSize()
        ) {
            Text(
                "D-12",
                modifier = Modifier
                    .align(
                        alignment = Alignment.CenterVertically
                    )
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = TripPathColors.BrandDefault,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(
                Modifier
                    .padding(horizontal = 12.dp)
                    .background(color = TripPathColors.BorderDefault)
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(3f)
            ) {
                Text(
                    title, style = TripPathTypography.LabelMedium.copy(
                        color = TripPathColors.TextDefault
                    )
                )
                Text(
                    dateInterval,
                    style = TripPathTypography.ParagraphMedium.copy(
                        color = TripPathColors.TextSubtler
                    )
                )
                Text(
                    city,
                    style = TripPathTypography.ParagraphMedium.copy(
                        color = TripPathColors.TextSubtler
                    )
                )
            }
            Box {
                if (onExtraButtonClick != null) {
                    IconButton(
                        onClick = onExtraButtonClick
                    ) {
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 500
)
@Composable
fun DDayTripCardPreview() {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        TripPathTheme {
            Column {
                DDayTripCard(
                    imgUrl = "https://dummyimage.com/600x400/000/fff",
                    title = "This is title",
                    dateInterval = "This is date-interval",
                    city = "City",
                    onExtraButtonClick = {}
                )
                DDayTripCard(
                    imgUrl = "https://dummyimage.com/600x400/000/fff",
                    title = "This is title",
                    dateInterval = "This is date-interval",
                    city = "City",
                    onExtraButtonClick = {}
                )
                DDayTripCard(
                    imgUrl = "https://dummyimage.com/600x400/000/fff",
                    title = "This is title",
                    dateInterval = "This is date-interval",
                    city = "City",
                    onExtraButtonClick = {}
                )
            }
        }
    }
}
