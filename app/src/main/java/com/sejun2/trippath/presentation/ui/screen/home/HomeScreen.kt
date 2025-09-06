import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.presentation.ui.component.SearchBar
import com.sejun2.trippath.presentation.ui.component.TripPathBottomNavBar
import com.sejun2.trippath.presentation.ui.component.TripPathBottomNavItems
import com.sejun2.trippath.presentation.ui.component.TripPathMainAppBar
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import com.sejun2.trippath.presentation.util.tripPathDefaultContentPadding

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier
) {
    HomeScreen(
        modifier
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        topBar = { TripPathMainAppBar() },
        bottomBar = {
            TripPathBottomNavBar(selectedItem = TripPathBottomNavItems.HOME)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
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
                SearchBar(Modifier)
            }
        }
    }
}

@Preview(locale = "ko")
@Composable
fun HomeScreenPreview() {
    TripPathTheme {
        HomeScreen(
            modifier = Modifier
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ko")
@Composable
fun HomeScreenPreviewDark() {
    TripPathTheme {
        HomeScreen(
            modifier = Modifier
        )
    }
}