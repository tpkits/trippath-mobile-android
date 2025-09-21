@file:OptIn(ExperimentalMaterial3Api::class)

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sejun2.trippath.presentation.ui.component.SearchBar
import com.sejun2.trippath.presentation.ui.component.TripPathBottomNavBar
import com.sejun2.trippath.presentation.ui.component.TripPathBottomNavItems
import com.sejun2.trippath.presentation.ui.component.TripPathMainAppBar
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import com.sejun2.trippath.presentation.util.tripPathDefaultContentPadding
import com.sejun2.trippath.presentation.viewmodel.AuthViewModel
import timber.log.Timber


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),
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
        }
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    openLoginBottomSheet: () -> Unit = {},
    closeLoginBottomSheet: () -> Unit = {},
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val scope = rememberCoroutineScope()

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
            Column {
                Spacer(Modifier.height(24.dp))
                SearchBar(Modifier, onValueChange = {
                    searchQuery = it
                }, value = searchQuery)
            }
        }
    }
}

@Preview(locale = "ko")
@Composable
fun HomeScreenPreview() {
    TripPathTheme {
        HomeScreen(
            modifier = Modifier,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ko")
@Composable
fun HomeScreenPreviewDark() {
    TripPathTheme {
        HomeScreen(
            modifier = Modifier,
        )
    }
}
