package com.bersyte.rent_a_car.features.customers.home.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationDateDialog(
onDismiss: () -> Unit,
onDatesSelected: (startDate: LocalDate, endDate: LocalDate) -> Unit
) {
    var startDateText by remember { mutableStateOf("") }
    var endDateText by remember { mutableStateOf("") }

    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }

    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Select Rental Dates",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Start Date Field
            OutlinedTextField(
                value = startDateText,
                onValueChange = {},
                label = { Text("Pick Up Date") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showStartDatePicker = true },
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // End Date Field
            OutlinedTextField(
                value = endDateText,
                onValueChange = {},
                label = { Text("Return Date") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Log.d("showEndDatePicker", "$showEndDatePicker")
                        showEndDatePicker = true
                        Log.d("showEndDatePicker", "$showEndDatePicker")
                    },
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    try {
                        val startDate = LocalDate.parse(startDateText, dateFormatter)
                        val endDate = LocalDate.parse(endDateText, dateFormatter)
                        onDatesSelected(startDate, endDate)
                    } catch (e: Exception) {
                        // Handle date parsing error
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = startDateText.isNotEmpty() && endDateText.isNotEmpty(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirm Reservation")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Show date pickers when triggered
    if (showStartDatePicker) {
        ShowDatePickerDialog(
            onDismiss = { showStartDatePicker = false },
            onDateSelected = { dateLong ->
                startDateText = dateLong.toString()
            }
        )
    }

    if (showEndDatePicker) {
        ShowDatePickerDialog(
            onDismiss = { showEndDatePicker = false },
            onDateSelected = { dateLong ->
                endDateText = dateLong.toString()
            }
        )
    }
}
