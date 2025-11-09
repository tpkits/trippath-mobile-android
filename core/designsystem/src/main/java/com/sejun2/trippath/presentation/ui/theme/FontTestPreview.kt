package com.sejun2.trippath.presentation.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FontTestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "TripPath 디자인 시스템",
            style = MaterialTheme.typography.headlineLarge
        )
        
        Text(
            text = "Pretendard Light",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Light)
        )
        
        Text(
            text = "Pretendard Regular",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal)
        )
        
        Text(
            text = "Pretendard Medium",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
        )
        
        Text(
            text = "Pretendard SemiBold",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        
        Text(
            text = "Pretendard Bold",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        
        Text(
            text = "여행 경로를 계획하고 공유하는 최고의 앱",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Text(
            text = "사람이 바라는 첫번째 부자시는 법",
            style = TripPathTypography.HeadingLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FontTestPreview() {
    TripPathTheme {
        FontTestScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FontTestPreviewDark() {
    TripPathTheme(darkTheme = true) {
        FontTestScreen()
    }
}