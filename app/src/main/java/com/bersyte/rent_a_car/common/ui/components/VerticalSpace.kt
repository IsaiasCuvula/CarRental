package com.bersyte.rent_a_car.common.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpace(height: Int = 16) {
    Spacer( modifier = Modifier.height(height.dp))
}
