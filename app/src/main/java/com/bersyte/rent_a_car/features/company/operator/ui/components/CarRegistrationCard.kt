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
import com.bersyte.rent_a_car.features.company.operator.data.models.CarRegistration
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@Composable
fun CarRegistrationCard(
    registration: CarRegistration,
    onApprove: () -> Unit,
    onReject: () -> Unit
) {

    val canceledBy = registration.canceledBy ?: ""
    val registeredBy = registration.registeredBy
    val processedBy = registration.processedBy ?: ""
    val registrationNumber = registration.registrationNumber
    val updatedAt = registration.updatedAt

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            registration.plate?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Submitted: ${AppHelpers.formatDateTime(registration.registrationDate)}",
                style = MaterialTheme.typography.bodySmall
            )
            if(updatedAt.isNotBlank()){
                Text(
                    text = "Updated: ${AppHelpers.formatDateTime(updatedAt)}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = "Registered by: $registeredBy",
                style = MaterialTheme.typography.bodyMedium
            )
            if(processedBy.isNotBlank()){
                Text(
                    text = "Processed by: $processedBy",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if(canceledBy.isNotBlank()){
                Text(
                    text = "Canceled by: $canceledBy",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = "Status: ${registration.status}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Number: $registrationNumber",
                style = MaterialTheme.typography.bodyMedium
            )

            if (registration.status == "PENDING") {
                Row (
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
