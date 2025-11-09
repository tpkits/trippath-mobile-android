package com.sejun2.trippath.presentation.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ColorImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme

@Composable
fun BaseTripCard(
    modifier: Modifier = Modifier,
    imgUrl: String,
    title: String,
    dateInterval: String,
    city: String,
    onExtraButtonClick: (() -> Unit)?
) {
    val sizeResolver = rememberConstraintsSizeResolver()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imgUrl)
            .size(sizeResolver)
            .build(),
    )

    BoxWithConstraints(
        modifier = modifier
            .widthIn(min = 200.dp, max = 400.dp)
            .aspectRatio(3f / 4f) // 3:4 비율 고정
            .clip(RoundedCornerShape(16.dp)),
    ) {
        val offsetY = maxHeight * 0.15f

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .then(sizeResolver),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .offset(
                    y = -offsetY
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TripPathColors.InverseCommon0
                ),
                fontSize = 28.sp
            )
            Spacer(Modifier.padding(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                )
                Spacer(Modifier.padding(horizontal = 4.dp))
                Text(
                    dateInterval,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = TripPathColors.InverseCommon0
                    ),
                    fontSize = 16.sp
                )
            }
            Spacer(Modifier.padding(4.dp))
            Row {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                )
                Spacer(Modifier.padding(horizontal = 6.dp))
                Text(
                    city,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = TripPathColors.InverseCommon0
                    ),
                    fontSize = 16.sp
                )
            }
        }

        IconButton(
            onClick = {
                onExtraButtonClick?.invoke()
            },
            modifier = Modifier
                .align(
                    Alignment.TopEnd
                )
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(46.dp)
            )
        }
    }
}

val previewHandler = AsyncImagePreviewHandler {
    ColorImage(Color.Red.toArgb())
}

@Preview(
    showBackground = true,
    backgroundColor = 0xffffffff,
    heightDp = 600,
    widthDp = 400
)
@Composable
fun BaseTripCardPreview() {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        TripPathTheme {
            BaseTripCard(
                imgUrl = "https://dummyimage.com/600x400/000/fff",
                title = "This is title",
                dateInterval = "This is date-interval",
                city = "City",
                onExtraButtonClick = {}
            )
        }
    }
}