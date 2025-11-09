package com.sejun2.trippath.presentation.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class TypographyItem(
    val name: String,
    val style: TextStyle,
    val category: String,
    val description: String = ""
)

@Composable
fun TypographySection(
    title: String,
    typographyItems: List<TypographyItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        typographyItems.forEach { item ->
            TypographyCard(
                typographyItem = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }
    }
}

@Composable
fun TypographyCard(
    typographyItem: TypographyItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Typography 정보
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = typographyItem.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${typographyItem.style.fontSize.value.toInt()}sp / ${typographyItem.style.lineHeight.value.toInt()}sp / ${typographyItem.style.fontWeight?.weight ?: "400"}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (typographyItem.description.isNotEmpty()) {
                Text(
                    text = typographyItem.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )

            // 실제 Typography 예시
            Text(
                text = "사람이 바라는 첫번째 부자시는 법",
                style = typographyItem.style,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun TripPathTypographyPreview() {
    val displayTypography = listOf(
        TypographyItem(
            "Display Large",
            TripPathTypography.DisplayLarge,
            "Display",
            "64sp - 큰 화면용 타이틀"
        ),
        TypographyItem(
            "Display Medium",
            TripPathTypography.DisplayMedium,
            "Display",
            "48sp - 중간 화면용 타이틀"
        ),
        TypographyItem(
            "Display Small",
            TripPathTypography.DisplaySmall,
            "Display",
            "36sp - 작은 화면용 타이틀"
        )
    )

    val headingTypography = listOf(
        TypographyItem("Heading XXL", TripPathTypography.HeadingXXL, "Heading", "32sp - 최대 헤딩"),
        TypographyItem("Heading XL", TripPathTypography.HeadingXL, "Heading", "28sp - 큰 헤딩"),
        TypographyItem(
            "Heading Large",
            TripPathTypography.HeadingLarge,
            "Heading",
            "24sp - 페이지 제목"
        ),
        TypographyItem(
            "Heading Medium",
            TripPathTypography.HeadingMedium,
            "Heading",
            "20sp - 섹션 제목"
        ),
        TypographyItem(
            "Heading Small",
            TripPathTypography.HeadingSmall,
            "Heading",
            "18sp - 서브섹션 제목"
        ),
        TypographyItem("Heading XS", TripPathTypography.HeadingXS, "Heading", "16sp - 작은 제목")
    )

    val labelTypography = listOf(
        TypographyItem("Label XL", TripPathTypography.LabelXL, "Label", "20sp - 큰 라벨"),
        TypographyItem("Label Large", TripPathTypography.LabelLarge, "Label", "18sp - 버튼, 중요 라벨"),
        TypographyItem("Label Medium", TripPathTypography.LabelMedium, "Label", "16sp - 일반 라벨"),
        TypographyItem("Label Small", TripPathTypography.LabelSmall, "Label", "14sp - 작은 라벨")
    )

    val paragraphTypography = listOf(
        TypographyItem(
            "Paragraph Large",
            TripPathTypography.ParagraphLarge,
            "Paragraph",
            "18sp - 큰 본문"
        ),
        TypographyItem(
            "Paragraph Medium",
            TripPathTypography.ParagraphMedium,
            "Paragraph",
            "16sp - 일반 본문"
        ),
        TypographyItem(
            "Paragraph Small",
            TripPathTypography.ParagraphSmall,
            "Paragraph",
            "14sp - 작은 본문"
        )
    )

    val uiReadingTypography = listOf(
        TypographyItem(
            "UI Reading Large",
            TripPathTypography.UIReadingLarge,
            "UI Reading",
            "16sp - UI 텍스트"
        ),
        TypographyItem(
            "UI Reading Medium",
            TripPathTypography.UIReadingMedium,
            "UI Reading",
            "14sp - 일반 UI"
        ),
        TypographyItem(
            "UI Reading Small",
            TripPathTypography.UIReadingSmall,
            "UI Reading",
            "12sp - 작은 UI"
        ),
        TypographyItem(
            "UI Reading XS",
            TripPathTypography.UIReadingXS,
            "UI Reading",
            "11sp - 최소 UI"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text(
                text = "TripPath Typography System",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            TypographySection(
                title = "Display",
                typographyItems = displayTypography
            )
        }

        item {
            TypographySection(
                title = "Headings",
                typographyItems = headingTypography
            )
        }

        item {
            TypographySection(
                title = "Labels",
                typographyItems = labelTypography
            )
        }

        item {
            TypographySection(
                title = "Paragraphs",
                typographyItems = paragraphTypography
            )
        }

        item {
            TypographySection(
                title = "UI Reading",
                typographyItems = uiReadingTypography
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TripPathTypographyPreviewLight() {
    TripPathTheme {
        TripPathTypographyPreview()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TripPathTypographyPreviewDark() {
    TripPathTheme(darkTheme = true) {
        TripPathTypographyPreview()
    }
}