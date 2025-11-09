package com.sejun2.trippath.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.core.designsystem.R
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme

@Composable
fun SearchBar(modifier: Modifier, onValueChange: (String) -> Unit = {}, value: String = "") {
    val backgroundColor = remember { TripPathColors.Neutral99 }

    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .fillMaxWidth()
            .background(
                color = backgroundColor,
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 16.dp)

        ) {
            Image(
                painter = painterResource(
                    R.drawable.ic_search,
                ),
                contentDescription = "image_ic_search"
            )
            Spacer(Modifier.width(8.dp))
            TextField(
                value = value,
                textStyle = MaterialTheme.typography.labelMedium.copy(
                    color = TripPathColors.TextDefault
                ),
                onValueChange = onValueChange,
                colors = TextFieldDefaults.colors(
                    errorContainerColor = Transparent,
                    focusedContainerColor = Transparent,
                    disabledContainerColor = Transparent,
                    unfocusedContainerColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    focusedIndicatorColor = Transparent
                ),

                placeholder = {
                    Text(
                        text = "어디로 가시나요?",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = TripPathColors.TextSubtler
                        )
                    )
                }
            )
        }
    }
}

@Preview()
@Composable
fun SearchBarPreview() {
    TripPathTheme {
        SearchBar(modifier = Modifier)
    }
}
