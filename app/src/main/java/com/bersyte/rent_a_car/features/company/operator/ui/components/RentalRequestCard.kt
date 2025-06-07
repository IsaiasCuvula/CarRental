package com.bersyte.rent_a_car.features.company.operator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import java.time.format.DateTimeFormatter
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun RentalRequestCard(
    rental: Rental,
    onApprove: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "${rental.carName} (${rental.carPlate})",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Status: ${rental.status}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Period: ${rental.rentStartDate.format(DateTimeFormatter.ofPattern("MMM dd"))} - ${rental.rentEndDate.format(DateTimeFormatter.ofPattern("MMM dd"))}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Amount: ${rental.totalPaidAmount}",
                style = MaterialTheme.typography.bodyMedium
            )

            if (rental.status == "PENDING") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onReject) {
                        Text("Reject")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onApprove) {
                        Text("Approve")
                    }
                }
            }
        }
    }
}
