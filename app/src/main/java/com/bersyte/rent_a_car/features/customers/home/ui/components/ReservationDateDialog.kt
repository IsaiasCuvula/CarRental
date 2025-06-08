package com.bersyte.rent_a_car.features.customers.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationDateDialog(
    onDismiss: () -> Unit,
    onDatesSelected: (startDate: LocalDate, endDate: LocalDate) -> Unit
) {
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Rental Dates") },
        text = {
            Column {
                // Start Date Picker
//                DatePicker(
//                    title = { Text("Pick Up Date") },
//                    selectedDate = startDate,
//                    onDateSelected = { startDate = it },
//                    state = TODO(),
//                    modifier = TODO(),
//                    dateFormatter = TODO(),
//                    headline = TODO(),
//                    showModeToggle = TODO(),
//                    colors = TODO()
//                )
//
//                Spacer(Modifier.height(16.dp))
//
//                // End Date Picker
//                DatePicker(
//                    title = "Return Date",
//                    selectedDate = endDate,
//                    onDateSelected = { endDate = it }
//                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    startDate?.let { start ->
                        endDate?.let { end ->
                            onDatesSelected(start, end)
                        }
                    }
                },
                enabled = startDate != null && endDate != null
            ) {
                Text("Confirm Reservation")
            }
        }
    )
}
