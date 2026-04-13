package com.example.demo_advanced_compose_patterns.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomVerticalLayout(
    modifier: Modifier = Modifier,
    spacing: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // 1. Measurement Phase
        val spacingPx = spacing.roundToPx()
        
        // Measure each child with the same constraints but subtract height for siblings
        var totalHeight = 0
        var maxWidth = 0
        
        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)
            totalHeight += placeable.height + (if (index < measurables.lastIndex) spacingPx else 0)
            maxWidth = maxOf(maxWidth, placeable.width)
            placeable
        }

        // 2. Layout Phase (Placement)
        layout(maxWidth, totalHeight) {
            var yPosition = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height + spacingPx
            }
        }
    }
}
