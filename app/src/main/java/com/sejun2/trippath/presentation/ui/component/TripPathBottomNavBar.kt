package com.sejun2.trippath.presentation.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.R
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme

enum class TripPathBottomNavItems(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {
    HOME(
        icon = R.drawable.ic_navigation_home,
        title = R.string.navigation_home
    ),
    SEARCH(
        icon = R.drawable.ic_navigation_search,
        title = R.string.navigation_search
    ),
    BOOKMARK(
        icon = R.drawable.ic_navigation_bookmark,
        title = R.string.navigation_bookmark
    ),
    BILLS(
        icon = R.drawable.ic_navigation_bills,
        title = R.string.navigation_bills
    ),
    PROFILE(
        icon = R.drawable.ic_navigation_profile,
        title = R.string.navigation_profile
    )
}

@Composable
fun TripPathBottomNavBar(modifier: Modifier = Modifier, selectedItem: TripPathBottomNavItems) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = TripPathColors.BorderDefault,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx()
                )
            },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TripPathBottomNavItem(
            isSelected = selectedItem == TripPathBottomNavItems.HOME,
            item = TripPathBottomNavItems.HOME
        )
        TripPathBottomNavItem(
            isSelected = selectedItem == TripPathBottomNavItems.SEARCH,
            item = TripPathBottomNavItems.SEARCH
        )
        TripPathBottomNavItem(
            isSelected = selectedItem == TripPathBottomNavItems.BOOKMARK,
            item = TripPathBottomNavItems.BOOKMARK
        )
        TripPathBottomNavItem(
            isSelected = selectedItem == TripPathBottomNavItems.BILLS,
            item = TripPathBottomNavItems.BILLS
        )
        TripPathBottomNavItem(
            isSelected = selectedItem == TripPathBottomNavItems.PROFILE,
            item = TripPathBottomNavItems.PROFILE
        )
    }
}

@Composable
fun TripPathBottomNavItem(
    modifier: Modifier = Modifier,
    item: TripPathBottomNavItems,
    isSelected: Boolean,
    selectedColor: Color = MaterialTheme.colorScheme.onBackground,
    unselectedColor: Color = Color(0xff9a9a9a),
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    if (isSelected) selectedColor else unselectedColor
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                stringResource(item.title),
                color = if (isSelected) selectedColor else unselectedColor,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Composable
fun TripPathBottomNavBarPreview() {
    TripPathTheme {
        TripPathBottomNavBar(
            selectedItem = TripPathBottomNavItems.HOME
        )
    }
}

@Preview
@Composable
fun TripPathBottomNavItemPreview() {
    TripPathTheme {
        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.HOME,
                    isSelected = false
                )
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.SEARCH,
                    isSelected = false
                )
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.BOOKMARK,
                    isSelected = false
                )
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.BILLS,
                    isSelected = false
                )
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.PROFILE,
                    isSelected = false
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.HOME,
                    isSelected = true
                )
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.SEARCH,
                    isSelected = true
                )
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.BOOKMARK,
                    isSelected = true
                )
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.BILLS,
                    isSelected = true
                )
                TripPathBottomNavItem(
                    item = TripPathBottomNavItems.PROFILE,
                    isSelected = true
                )
            }
        }
    }
}