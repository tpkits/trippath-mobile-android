package com.sejun2.trippath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.ui.NavDisplay
import com.sejun2.trippath.core.extension.back
import com.sejun2.trippath.core.navigation.TripPathAppState
import com.sejun2.trippath.core.navigation.rememberTripPathAppState
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val tripPathAppState = rememberTripPathAppState()

            TripPathTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = tripPathAppState.navBackStack,
                        entryProvider = tripPathAppState.entryProvider,
                        onBack = { index ->
                            tripPathAppState.navBackStack.back()
                        }
                    )
                }
            }
        }
    }
}
