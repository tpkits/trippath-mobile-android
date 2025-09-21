@file:OptIn(ExperimentalMaterial3Api::class)

import android.content.res.Configuration
import androidx.collection.LongLongPair
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.presentation.ui.component.SearchBar
import com.sejun2.trippath.presentation.ui.component.TripPathBottomNavBar
import com.sejun2.trippath.presentation.ui.component.TripPathBottomNavItems
import com.sejun2.trippath.presentation.ui.component.TripPathMainAppBar
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import com.sejun2.trippath.presentation.util.tripPathDefaultContentPadding
import timber.log.Timber


@Composable
fun rememberLoginDialogState(): Pair<Boolean, SheetState> {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Button(onClick = {
                showBottomSheet = false
            }) {
                Text("Hide bottom sheet")
            }
        }
    }
    return Pair<Boolean, SheetState>(showBottomSheet, sheetState)
}

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    val loginDialogState = rememberLoginDialogState()

    HomeScreen(
        modifier,
        loginDialogState.first,
        onLoginDialogStateChanged = {}
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    openLoginDialog: Boolean,
    onLoginDialogStateChanged: (Boolean) -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

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
                    Timber.d("openLoginDialog = $openLoginDialog")
                    onLoginDialogStateChanged(!openLoginDialog)
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
            openLoginDialog = true,
            onLoginDialogStateChanged = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ko")
@Composable
fun HomeScreenPreviewDark() {
    TripPathTheme {
        HomeScreen(
            modifier = Modifier,
            openLoginDialog = true,
            onLoginDialogStateChanged = {}
        )
    }
}