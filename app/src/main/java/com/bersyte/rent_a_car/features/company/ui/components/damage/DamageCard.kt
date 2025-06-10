package com.bersyte.rent_a_car.features.company.ui.components.damage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.company.data.models.VehicleDamage
import com.bersyte.rent_a_car.utils.enums.VehicleDamageStatus
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@Composable
fun DamageCard(damage: VehicleDamage) {

    val status = VehicleDamageStatus.valueOf(damage.status)


    var showUpdateDamageOption by remember { mutableStateOf<VehicleDamage?>(null) }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.clickable(
            onClick = {
                showUpdateDamageOption = damage
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = damage.damageLocation,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                DamageStatusChip(status = status)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = damage.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            DamageDetailRow(
                icon = Icons.Default.MonetizationOn,
                text = "Estimated cost: $${damage.estimatedRepairCost}"
            )

            DamageDetailRow(
                icon = Icons.Default.CalendarToday,
                text = "Reported: ${AppHelpers.formatDateTime(damage.reportedAt)}"
            )

            if (status == VehicleDamageStatus.FIXED) {
                DamageDetailRow(
                    icon = Icons.Default.Build,
                    text = "Fixed: ${AppHelpers.formatDateTime(damage.fixedAt)}"
                )
            }

            if (status == VehicleDamageStatus.PAID) {
                DamageDetailRow(
                    icon = Icons.Default.Payment,
                    text = "Paid: ${AppHelpers.formatDateTime(damage.paidAt)}"
                )
            }
        }
    }

    showUpdateDamageOption?.let { selectedDamage ->
        UpdateDamage(
            onDismissRequest ={ showUpdateDamageOption = null},
            damage = selectedDamage,
        )
    }
}
