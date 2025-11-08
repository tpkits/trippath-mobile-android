@file:OptIn(ExperimentalMaterial3Api::class)

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.LocalAsyncImagePreviewHandler
import com.sejun2.trippath.presentation.ui.component.TripPathBottomNavBar
import com.sejun2.trippath.presentation.ui.component.TripPathBottomNavItems
import com.sejun2.trippath.presentation.ui.component.TripPathMainAppBar
import com.sejun2.trippath.presentation.ui.component.card.BaseTripCard
import com.sejun2.trippath.presentation.ui.component.card.DDayTripCard
import com.sejun2.trippath.presentation.ui.component.card.LastTripCard
import com.sejun2.trippath.presentation.ui.component.card.previewHandler
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import com.sejun2.trippath.presentation.util.tripPathDefaultContentPadding
import com.sejun2.trippath.presentation.viewmodel.AuthViewModel
import timber.log.Timber


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),
    navigateToTripMainScreen: () -> Unit = {},
) {
    var loginBottomSheetVisibility by rememberSaveable { mutableStateOf(false) }

    LoginBottomSheetDialog(
        isVisible = loginBottomSheetVisibility,
        loginWithOauth = authViewModel::loginWithOauth
    ) {
        Timber.d("loginBottomSheetVisibility: $it")
        loginBottomSheetVisibility = it
    }

    HomeScreen(
        modifier,
        openLoginBottomSheet = {
            loginBottomSheetVisibility = true
        },
        closeLoginBottomSheet = {
            loginBottomSheetVisibility = false
        },
        onExtraButtonClick = navigateToTripMainScreen
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    openLoginBottomSheet: () -> Unit = {},
    closeLoginBottomSheet: () -> Unit = {},
    onExtraButtonClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        topBar = { TripPathMainAppBar() },
        bottomBar = {
            TripPathBottomNavBar(selectedItem = TripPathBottomNavItems.HOME)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openLoginBottomSheet()
                },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Home",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .tripPathDefaultContentPadding()
        ) {
            Column(
                modifier = Modifier.verticalScroll(
                    state = rememberScrollState()
                )
            ) {
                Spacer(Modifier.height(24.dp))
//                SearchBar(Modifier, onValueChange = {
//                    searchQuery = it
//                }, value = searchQuery)
                Heading(
                    text = "내 여행",
                )
                Spacer(
                    Modifier.padding(12.dp)
                )
                BaseTripCard(
                    imgUrl = "https://dummyimage.com/600x400/000/fff",
                    title = "This is title",
                    dateInterval = "This is date-interval",
                    city = "City",
                    onExtraButtonClick = onExtraButtonClick
                )
                Spacer(
                    Modifier.padding(12.dp)
                )
                Heading(
                    text = "예정된 여행",
                    onTitleClick = {}
                )
                Spacer(
                    Modifier.padding(12.dp)
                )
                DDayTripCardListView()
                Spacer(
                    Modifier.padding(12.dp)
                )
                Heading(
                    text = "지난 여행",
                    onTitleClick = {},
                    trailing = {
                        Text(
                            text = "필터",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                )
                Spacer(
                    Modifier.padding(12.dp)
                )
                LastTripCardListView()
            }
        }
    }
}

@Composable
fun LastTripCardListView() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 0.dp, max = 450.dp)
    ) {
        item {
            LastTripCard(
                imgUrl = "https://dummyimage.com/600x400/000/fff",
                title = "This is title",
                dateInterval = "This is date-interval",
                city = "City",
                onExtraButtonClick = {}
            )
            LastTripCard(
                imgUrl = "https://dummyimage.com/600x400/000/fff",
                title = "This is title",
                dateInterval = "This is date-interval",
                city = "City",
                onExtraButtonClick = {}
            )
            LastTripCard(
                imgUrl = "https://dummyimage.com/600x400/000/fff",
                title = "This is title",
                dateInterval = "This is date-interval",
                city = "City",
                onExtraButtonClick = {}
            )
            LastTripCard(
                imgUrl = "https://dummyimage.com/600x400/000/fff",
                title = "This is title",
                dateInterval = "This is date-interval",
                city = "City",
                onExtraButtonClick = {}
            )
        }
    }
}

@Composable
fun DDayTripCardListView() {

    val pagerState = rememberPagerState(pageCount = {
        3
    })

    Column {
        HorizontalPager(pagerState) {
            DDayTripCard(
                imgUrl = "https://dummyimage.com/600x400/000/fff",
                title = "This is title",
                dateInterval = "This is date-interval",
                city = "City",
                onExtraButtonClick = {}
            )
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(12.dp)
                )
            }
        }
    }
}

@Composable
fun Heading(
    modifier: Modifier = Modifier,
    text: String,
    onTitleClick: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text, style = MaterialTheme.typography.headlineLarge
        )
        if (onTitleClick != null)
            IconButton(
                onClick = onTitleClick,
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
        Spacer(modifier = Modifier.weight(1f))
        trailing?.invoke()
    }
}

@Preview(locale = "ko", heightDp = 1500)
@Composable
fun HomeScreenPreview() {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        TripPathTheme {
            HomeScreen(
                modifier = Modifier,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ko", heightDp = 1500)
@Composable
fun HomeScreenPreviewDark() {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        TripPathTheme {
            HomeScreen(
                modifier = Modifier,
            )
        }
    }
}
