package com.bersyte.rent_a_car.features.admin.dashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.admin.dashboard.data.Operator


@Composable
fun OperatorCard(
    operator: Operator,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = operator.name.take(1).uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = operator.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = operator.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            AssistChip(
                onClick = {},
                label = {
                    Text(
                        text = if (operator.isActive) "Active" else "Inactive",
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (operator.isActive) {
                        Color(0xFF4CAF50).copy(alpha = 0.2f)
                    } else {
                        Color(0xFFF44336).copy(alpha = 0.2f)
                    },
                    labelColor = if (operator.isActive) Color(0xFF4CAF50) else Color(0xFFF44336)
                ),
//                border = AssistChipDefaults.assistChipBorder(
//                    borderColor = if (operator.isActive) Color(0xFF4CAF50) else Color(0xFFF44336),
//                    borderWidth = 1.dp
//                )
            )
        }
    }
}
