package com.sejun2.trippath.presentation.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IntroRoute(
    modifier: Modifier = Modifier,
    onNavigateToMain: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    IntroScreen(
        modifier,
        onNavigateToMain,
        onBack
    )

}

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    onNavigateToMain: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    Surface {
        // Sample composable
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Intro Screen")
            TextButton(
                onClick = {
                    onNavigateToMain()
                }
            ) {
                Text(text = "Go to Main")
            }
            TextButton(
                onClick = onBack
            ) {
                Text(text = "Go Back")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen()
}

