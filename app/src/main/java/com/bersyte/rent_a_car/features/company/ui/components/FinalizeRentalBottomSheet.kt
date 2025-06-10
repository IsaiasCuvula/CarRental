package com.bersyte.rent_a_car.features.company.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.company.data.models.FinalizeRentalRequest
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bersyte.rent_a_car.common.ui.components.CommonTextField
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.utils.enums.ReturnDamageStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalizeRentalBottomSheet(
    onSave: (FinalizeRentalRequest) -> Unit,
    onDismissRequest: () -> Unit,
    rental: Rental
) {
    var damageStatus by remember { mutableStateOf(ReturnDamageStatus.NONE) }
    var returnConditionReport by remember { mutableStateOf("") }
    var returnedMileage by remember { mutableStateOf("") }
    var damageDescription by remember { mutableStateOf("") }
    var estimatedRepairCost by remember { mutableStateOf("") }
    var damageLocation by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Finalize Rental",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            CommonTextField(
                value = returnConditionReport,
                onValueChange = { returnConditionReport = it },
                label = "Return Condition Report",
                isError = returnConditionReport.isBlank()
            )

            CommonTextField(
                value = returnedMileage,
                onValueChange = { returnedMileage = it },
                label = "Returned Mileage",
                isError = returnedMileage.isBlank(),
                keyboardType = KeyboardType.Number
            )

            DamageStatusSelector(
                selectedDamage = damageStatus.name,
                onDamageSelected = { damageStatus = it },
                modifier = Modifier.fillMaxWidth(),
            )

            if (damageStatus != ReturnDamageStatus.NONE) {
                CommonTextField(
                    value = damageDescription,
                    onValueChange = { damageDescription = it },
                    label = "Damage Description",
                )

                CommonTextField(
                    value = estimatedRepairCost,
                    onValueChange = { estimatedRepairCost = it },
                    label = "Estimated Repair Cost",
                    keyboardType = KeyboardType.Number
                )

                CommonTextField(
                    value = damageLocation,
                    onValueChange = { damageLocation = it },
                    label = "Damage Location",
                )
            }

            Button (
                onClick = {
                    val request = FinalizeRentalRequest(
                        rentalCode = rental.rentalCode,
                        returnConditionReport = returnConditionReport,
                        returnedMileage = returnedMileage.toLongOrNull() ?: 0,
                        damageStatus = damageStatus.name,
                        damageDescription = if (damageStatus != ReturnDamageStatus.NONE) damageDescription else "",
                        estimatedRepairCost = if (damageStatus != ReturnDamageStatus.NONE) estimatedRepairCost.toLongOrNull() ?: 0 else 0,
                        damageLocation = if (damageStatus != ReturnDamageStatus.NONE) damageLocation else ""
                    )
                    onSave(request)
                },
                enabled = returnedMileage.isNotBlank() && returnConditionReport.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Finalize Rental")
            }
        }
    }
}
