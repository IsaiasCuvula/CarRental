package com.bersyte.rent_a_car.features.company.ui.components.damage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.utils.enums.VehicleDamageStatus
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


@Composable
fun DamageStatusChip(status: VehicleDamageStatus) {
    val (text, color) = when (status) {
        VehicleDamageStatus.PENDING -> "Reported" to Color.Red
        VehicleDamageStatus.PAID -> "Paid" to Color(0xFFFFA000)
        VehicleDamageStatus.FIXED -> "Fixed" to Color(0xFF388E3C)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(
                color = color.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
        ).padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
         Text(
             text = text,
             color = color,
             style = MaterialTheme.typography.labelSmall,
             fontWeight = FontWeight.Bold
         )
    }
}
