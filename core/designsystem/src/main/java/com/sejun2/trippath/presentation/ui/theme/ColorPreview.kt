package com.sejun2.trippath.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ColorItem(
    val name: String,
    val color: Color,
    val description: String = ""
)

@Composable
fun ColorPaletteSection(
    title: String,
    colors: List<ColorItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        colors.chunked(2).forEach { rowColors ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowColors.forEach { colorItem ->
                    ColorCard(
                        colorItem = colorItem,
                        modifier = Modifier.weight(1f)
                    )
                }
                
                // 홀수 개수일 때 빈 공간 채우기
                if (rowColors.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ColorCard(
    colorItem: ColorItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(colorItem.color)
            )
            
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = colorItem.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                
                Text(
                    text = "#${colorItem.color.value.toString(16).uppercase().takeLast(6)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                if (colorItem.description.isNotEmpty()) {
                    Text(
                        text = colorItem.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun TripPathColorPreview() {
    val brandColors = listOf(
        ColorItem("Brand Default", TripPathColors.BrandDefault, "Primary brand color"),
        ColorItem("Brand Subtle", TripPathColors.BrandSubtle, "Lighter brand variant"),
        ColorItem("Brand Subtlest", TripPathColors.BrandSubtlest, "Lightest brand variant"),
        ColorItem("Brand Bold", TripPathColors.BrandBold, "Bold brand variant")
    )
    
    val neutralColors = listOf(
        ColorItem("Neutral 99", TripPathColors.Neutral99),
        ColorItem("Neutral 95", TripPathColors.Neutral95),
        ColorItem("Neutral 90", TripPathColors.Neutral90),
        ColorItem("Neutral 80", TripPathColors.Neutral80),
        ColorItem("Neutral 70", TripPathColors.Neutral70),
        ColorItem("Neutral 60", TripPathColors.Neutral60),
        ColorItem("Neutral 50", TripPathColors.Neutral50),
        ColorItem("Neutral 40", TripPathColors.Neutral40),
        ColorItem("Neutral 30", TripPathColors.Neutral30),
        ColorItem("Neutral 20", TripPathColors.Neutral20)
    )
    
    val accentColors = listOf(
        ColorItem("Lime", TripPathColors.Lime),
        ColorItem("Purple", TripPathColors.Purple),
        ColorItem("Violet", TripPathColors.Violet),
        ColorItem("Light Blue", TripPathColors.LightBlue),
        ColorItem("Yellow", TripPathColors.Yellow),
        ColorItem("Orange", TripPathColors.Orange),
        ColorItem("Red Orange", TripPathColors.RedOrange)
    )
    
    val systemColors = listOf(
        ColorItem("Danger", TripPathColors.SystemDanger, "Error states"),
        ColorItem("Success", TripPathColors.SystemSuccess, "Success states"),
        ColorItem("Warning", TripPathColors.SystemWarning, "Warning states"),
        ColorItem("Information", TripPathColors.SystemInformation, "Info states")
    )
    
    val semanticColors = listOf(
        ColorItem("Text Default", TripPathColors.TextDefault, "Primary text"),
        ColorItem("Text Subtle", TripPathColors.TextSubtle, "Secondary text"),
        ColorItem("Background White", TripPathColors.BackgroundWhite, "Main background"),
        ColorItem("Background Neutral", TripPathColors.BackgroundNeutral, "Secondary background"),
        ColorItem("Border Default", TripPathColors.BorderDefault, "Default borders"),
        ColorItem("Border Bold", TripPathColors.BorderBold, "Emphasized borders")
    )
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text(
                text = "TripPath Design System",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        
        item {
            ColorPaletteSection(
                title = "Brand Colors",
                colors = brandColors
            )
        }
        
        item {
            ColorPaletteSection(
                title = "Neutral Colors",
                colors = neutralColors
            )
        }
        
        item {
            ColorPaletteSection(
                title = "Accent Colors",
                colors = accentColors
            )
        }
        
        item {
            ColorPaletteSection(
                title = "System Colors",
                colors = systemColors
            )
        }
        
        item {
            ColorPaletteSection(
                title = "Semantic Colors",
                colors = semanticColors
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TripPathColorPreviewLight() {
    TripPathTheme {
        TripPathColorPreview()
    }
}