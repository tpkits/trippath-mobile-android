@file:OptIn(ExperimentalMaterial3Api::class)

package com.sejun2.trippath.presentation.ui.screen.tripmain

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import timber.log.Timber

@Composable
fun TripMainRoute(
    modifier: Modifier = Modifier,
) {
    TripMainScreen(modifier)
}

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

    val density = LocalDensity.current
    val dragThreshold = with(density) { 50.dp.toPx() } // threshold in pixels

    var accumulatedDrag by remember { mutableStateOf(0f) }
    var swipeEnabled by remember { mutableStateOf(true) }

    // Expanded와 PartiallyExpanded 상태의 offset을 저장
    var expandedOffset by remember { mutableStateOf<Float?>(null) }
    var partiallyExpandedOffset by remember { mutableStateOf<Float?>(null) }

    // 각 상태에서의 offset 값을 기록
    LaunchedEffect(bottomSheetState.currentValue) {
        try {
            val currentOffset = bottomSheetState.requireOffset()
            when (bottomSheetState.currentValue) {
                SheetValue.Expanded -> expandedOffset = currentOffset
                SheetValue.PartiallyExpanded -> partiallyExpandedOffset = currentOffset
                else -> {}
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    val sheetProgress by remember {
        derivedStateOf {
            try {
                val currentOffset = bottomSheetState.requireOffset()
                val expanded = expandedOffset
                val partiallyExpanded = partiallyExpandedOffset

                if (expanded == null || partiallyExpanded == null) {
                    return@derivedStateOf 0f
                }

                val progress = if (partiallyExpanded != expanded) {
                    ((partiallyExpanded - currentOffset) / (partiallyExpanded - expanded)).coerceIn(
                        0f,
                        1f
                    )
                } else {
                    0f
                }

                progress
            } catch (_: Exception) {
                return@derivedStateOf 0f
            }
        }
    }

    val isExpanded by remember(sheetProgress) {
        derivedStateOf {
            sheetProgress == 1f
        }
    }

    val cornerRadius by animateDpAsState(
        targetValue = if (isExpanded) 0.dp else 24.dp,
        label = "cornerRadius"
    )

    val appBarAlpha by animateFloatAsState(
        targetValue = if (isExpanded) sheetProgress else 0f,
        label = "appBarAlpha"
    )

    val isAppbarEnabled by derivedStateOf { appBarAlpha > 0.0f }

    val systemBarTopHeight = WindowInsets.systemBars
        .asPaddingValues()
        .calculateTopPadding()

    val bottomNavigationHeight = WindowInsets.systemBars
        .asPaddingValues()
        .calculateBottomPadding()

    LaunchedEffect(isExpanded) {
        if (!isExpanded) {
            accumulatedDrag = 0f
            swipeEnabled = true
        } else {
            swipeEnabled = false
            accumulatedDrag = 0f
        }
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (isExpanded && !swipeEnabled) {
                    if (available.y > 0) {
                        // 아래로 드래그 중 (pull down)
                        accumulatedDrag += available.y

                        if (accumulatedDrag >= dragThreshold) {
                            swipeEnabled = true
                        }
                    }
                }

                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                return Offset.Zero
            }
        }
    }




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
        sheetSwipeEnabled = swipeEnabled,
        sheetContent = {
            Box(
                modifier = Modifier
                    .heightIn(max = rememberScreenHeightDp() - TopAppBarDefaults.TopAppBarExpandedHeight - systemBarTopHeight - bottomNavigationHeight + 1.dp)
                    .nestedScroll(nestedScrollConnection)
            ) {
                BottomSheetContent(isExpanded = isExpanded)
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackgroundContent(
                isAppbarEnabled = isAppbarEnabled,
            )
        }
    }
}

@Composable
private fun BackgroundContent(
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
private fun BottomSheetContent(isExpanded: Boolean) {
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

        AnimatedVisibility(
            visible = !isExpanded,
            enter = fadeIn(
                animationSpec = TweenSpec(
                    durationMillis = 300,
                )
            ),
            exit = fadeOut(
                animationSpec = TweenSpec(
                    durationMillis = 150,
                )
            ),
        ) {
            DragHandle()
        }
    }
}

@Composable
fun rememberScreenHeightDp(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp.dp// 화면 높이를 Dp 단위로 반환
}

@Composable
private fun DragHandle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(4.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(2.dp)
                )
        )
    }
}


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
