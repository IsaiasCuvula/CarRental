package com.bersyte.rent_a_car.features.customers.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.utils.helpers.AppHelpers
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationDateBottomSheet(
    onDismiss: () -> Unit,
    onDatesSelected: (startDate: LocalDate, endDate: LocalDate) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    val today = LocalDate.now()

    var startDateText by remember { mutableStateOf(today) }
    var endDateText by remember { mutableStateOf(today.plusDays(1)) }

    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = "Select Rental Dates",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Pick Up Date Row
            DateSelectionRow(
                label = "Pick Up Date",
                selectedDate = startDateText,
                onClick = { showStartDatePicker = true }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Return Date Row
            DateSelectionRow(
                label = "Return Date",
                selectedDate = endDateText,
                onClick = { showEndDatePicker = true }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Confirm Button
            Button(
                onClick = { onDatesSelected(startDateText, endDateText) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirm Reservation")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Cancel Button
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }
        }
    }

    // Date Pickers
    if (showStartDatePicker) {
        ShowDatePickerDialog(
            onDismiss = { showStartDatePicker = false },
            onDateSelected = { dateLong ->
                dateLong?.let {
                    startDateText = AppHelpers.longToLocalDateTime(it).toLocalDate()
                }
            }
        )
    }

    if (showEndDatePicker) {
        ShowDatePickerDialog(
            onDismiss = { showEndDatePicker = false },
            onDateSelected = { dateLong ->
                dateLong?.let {
                    endDateText = AppHelpers.longToLocalDateTime(it).toLocalDate()
                }
            }
        )
    }
}

@Composable
private fun DateSelectionRow(
    label: String,
    selectedDate: LocalDate,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Select date",
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = selectedDate.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(label)
        }
    }
}
