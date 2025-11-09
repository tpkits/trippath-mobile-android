package com.sejun2.trippath.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import com.sejun2.trippath.presentation.ui.screen.auth.IntroRoute
import com.sejun2.trippath.presentation.ui.screen.home.HomeRoute
import com.sejun2.trippath.presentation.ui.screen.tripmain.TripMainRoute

/// 앱 전반적인 설정이 저장되는 composable
@Composable
fun rememberTripPathAppState(): TripPathAppState {

    // navBackstack
    // Jetpack Navigation3 에서 관리하는 BackStack 입니다.
    // 자세한 내용은 Jetpack Navigation3 문서를 참고하세요.
    // ("https://developer.android.com/guide/navigation/navigation-3?hl=ko")
    val navBackStack = rememberNavBackStack(IntroNavKey)

    // entryProvider
    // 이곳에서 네비게이션 라우트를 관리합니다.
    // TODO: EntryProvider 을 별도로 분리
    val entryProvider = entryProvider<NavKey> {
        entry<IntroNavKey> {
            IntroRoute(
                modifier = Modifier,
                closeIntroWithoutAuthentication = {
                    navBackStack.removeAt(navBackStack.lastIndex)
                    navBackStack.add(HomeNavKey)
                },
                onAuthSuccess = {
                    navBackStack.removeAt(navBackStack.lastIndex)
                    navBackStack.add(HomeNavKey)
                }
            )
        }
        entry<HomeNavKey> {
            HomeRoute(
                modifier = Modifier,
                navigateToTripMainScreen = { navBackStack.add(TripMainNavKey) }
            )
        }
        entry<TripMainNavKey> {
            TripMainRoute(
                modifier = Modifier
            )
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
    val navBackStack: NavBackStack,
    val entryProvider: (NavKey) -> NavEntry<NavKey>,
)
