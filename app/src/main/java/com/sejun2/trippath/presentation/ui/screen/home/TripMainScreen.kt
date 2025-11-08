@file:OptIn(ExperimentalMaterial3Api::class)

package com.sejun2.trippath.presentation.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import timber.log.Timber

@Composable
fun TripMainRoute(
    modifier: Modifier = Modifier,
) {
    TripMainScreen(modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripMainScreen(
    modifier: Modifier = Modifier,
    initialSheetValue: SheetValue = SheetValue.PartiallyExpanded
) {
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = initialSheetValue,
        skipHiddenState = true,
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )

    val sheetBenchmark =
        64.dp + WindowInsets.systemBars
            .asPaddingValues()
            .calculateTopPadding() + WindowInsets.systemBars
            .asPaddingValues()
            .calculateBottomPadding() + 16.dp

    val sheetProgress by remember {
        derivedStateOf {
            try {
                val currentOffset = bottomSheetState.requireOffset()

                if (currentOffset.dp < sheetBenchmark + 100.dp) return@derivedStateOf 1f else return@derivedStateOf 0f
            } catch (e: Exception) {
                return@derivedStateOf 0f
            }
        }
    }

    val cornerRadius by animateDpAsState(
        targetValue = if (sheetProgress >= 0.95f) 0.dp else 24.dp,
        label = "cornerRadius"
    )

    val appBarAlpha by animateFloatAsState(
        targetValue = if (sheetProgress >= 0.95f) sheetProgress else 0f,
        label = "appBarAlpha"
    )

    val isAppbarEnabled = derivedStateOf { appBarAlpha > 0.0f }

    val systemBarTopHeight = WindowInsets.systemBars
        .asPaddingValues()
        .calculateTopPadding()

    val bottomNavigationHeight = WindowInsets.systemBars
        .asPaddingValues()
        .calculateBottomPadding()



    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(
            topStart = cornerRadius,
            topEnd = cornerRadius,
        ),
        sheetPeekHeight = rememberScreenHeightDp() * 3 / 5,
        sheetDragHandle = {},
        sheetContainerColor = MaterialTheme.colorScheme.surface,
        sheetShadowElevation = 0.dp,
        sheetContent = {
            Box(
                modifier = Modifier.heightIn(max = rememberScreenHeightDp() - TopAppBarDefaults.TopAppBarExpandedHeight - systemBarTopHeight - bottomNavigationHeight + 1.dp)
            ) {
                BottomSheetContent()
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackgroundContent(
                isAppbarEnabled = isAppbarEnabled.value,
                appBarAlpha = appBarAlpha
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BackgroundContent(
    appBarAlpha: Float,
    isAppbarEnabled: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            Text(
                text = "Title",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Title",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Title",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Title",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Title",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Title",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        AnimatedVisibility(
            visible = isAppbarEnabled,
            modifier = Modifier,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .height(TopAppBarDefaults.TopAppBarExpandedHeight)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /* TODO: Navigation */ },
                        enabled = isAppbarEnabled,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로가기",
                        )
                    }
                    IconButton(
                        onClick = { /* TODO: Action */ },
                        enabled = isAppbarEnabled,
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "더보기",
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = !isAppbarEnabled,
            modifier = Modifier,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TopAppBarDefaults.TopAppBarExpandedHeight)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /* TODO: Navigation */ },
                        enabled = !isAppbarEnabled,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로가기",
                        )
                    }
                    IconButton(
                        onClick = { /* TODO: Action */ },
                        enabled = !isAppbarEnabled,
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "더보기",
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomSheetContent() {
    Box(modifier = Modifier.background(color = Color.Transparent)) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(30) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "여행지 ${index + 1}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "여행지 설명이 여기에 표시됩니다",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    if (index < 29) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun rememberScreenHeightDp(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp.dp// 화면 높이를 Dp 단위로 반환
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TripMainScreenPreview() {
    TripPathTheme {
        TripMainScreen()
    }
}

@Preview
@Composable
fun TripMainScreenPreview2() {
    TripPathTheme() {
        TripMainScreen(initialSheetValue = SheetValue.Expanded)
    }
}
