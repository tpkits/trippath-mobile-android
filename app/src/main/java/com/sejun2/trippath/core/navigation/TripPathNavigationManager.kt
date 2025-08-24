package com.sejun2.trippath.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import com.sejun2.trippath.core.extension.back
import com.sejun2.trippath.presentation.ui.screen.auth.IntroRoute

/// 앱 전반적인 설정이 저장되는 composable
@Composable
fun rememberTripPathAppState(): TripPathAppState {

    // navBackstack
    // Jetpack Navigation3 에서 관리하는 BackStack 입니다.
    // 자세한 내용은 Jetpack Navigation3 문서를 참고하세요.
    // ("https://developer.android.com/guide/navigation/navigation-3?hl=ko")
    val navBackStack = remember { mutableStateListOf<TripPathNavKey>(IntroNavKey) }

    // entryProvider
    // 이곳에서 네비게이션 라우트를 관리합니다.
    val entryProvider = entryProvider<TripPathNavKey> {
        entry<IntroNavKey> {
            IntroRoute(
                modifier = Modifier,
            )
        }
        entry<MainNavKey> {
            Text("This is sample main screen")
        }
    }

    return remember {
        TripPathAppState(
            navBackStack = navBackStack,
            entryProvider = entryProvider
        )
    }
}

@Stable
class TripPathAppState(
    val navBackStack: SnapshotStateList<TripPathNavKey>,
    val entryProvider: (TripPathNavKey) -> NavEntry<TripPathNavKey>,
)
