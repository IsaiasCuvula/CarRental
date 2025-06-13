package com.bersyte.rent_a_car.common.ui.components.car

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    initialColor: Color = Color.Red,
    onColorSelected: (Int) -> Unit
) {
    val colors = listOf(
        Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.White,Color.Gray,
        Color.Magenta, Color.Cyan, Color.Black, Color.White,Color.LightGray,
        Color(0xFF6200EE), Color(0xFF03DAC6), Color(0xFF018786),
        Color(0xFFBB86FC), Color(0xFF3700B3), Color(0xFFF44336),
        Color(0xFFE91E63), Color(0xFF9C27B0), Color(0xFF673AB7)
    )

    var selectedColor by remember { mutableStateOf(initialColor) }

    // Initial callback
    LaunchedEffect(Unit) {
        onColorSelected(selectedColor.toArgb())
    }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            colors.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color, CircleShape)
                        .border(
                            width = if (color == selectedColor) 2.dp else 1.dp,
                            color = if (color == selectedColor) Color.Black else Color.Gray,
                            shape = CircleShape
                        )
                        .clickable {
                            selectedColor = color
                            onColorSelected(color.toArgb())
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (color == selectedColor) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = if (color.isLightColor()) Color.Black else Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

// Extension to check if color is light
fun Color.isLightColor(): Boolean {
    return (red * 0.299 + green * 0.587 + blue * 0.114) > 0.5
}

// Extension functions for color conversion (same as before)
fun Color.toArgb(): Int {
    return (alpha * 255).toInt() shl 24 or
            (red * 255).toInt() shl 16 or
            (green * 255).toInt() shl 8 or
            (blue * 255).toInt()
}

fun Int.toHexString(): String {
    return String.format("#%08X", this)
}
