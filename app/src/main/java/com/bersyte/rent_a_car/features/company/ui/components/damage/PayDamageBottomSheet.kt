package com.bersyte.rent_a_car.features.company.ui.components.damage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import java.time.format.DateTimeFormatter
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.ui.Alignment
import com.bersyte.rent_a_car.features.company.data.models.PayDamageRequest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayDamageBottomSheet(
    carPlate: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSelected: (PayDamageRequest) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    var amount by remember { mutableStateOf(0) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val dateFormatter = remember { DateTimeFormatter.ofPattern("MMM dd, yyyy") }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Enter Amount",
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = amount,
                onValueChange = {
                    if (it.isEmpty() || it.toLongOrNull() != null) {
                        amount = it
                        it.toLongOrNull()?.let {
                            longValue -> amount = longValue
                        }
                    } },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Selected Date: ${selectedDate.format(dateFormatter)}",
                    style = MaterialTheme.typography.bodyMedium
                )

                val datePickerState = rememberDatePickerState(
                    initialSelectedDateMillis = selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                )
                val showDatePicker = remember { mutableStateOf(false) }

                Button(onClick = { showDatePicker.value = true }) {
                    Text("Pick Date")
                }

                if (showDatePicker.value) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker.value = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        selectedDate = Instant.ofEpochMilli(millis)
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate()

                                    }
                                    showDatePicker.value = false
                                }
                            ) {
                                Text("OK")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val request = PayDamageRequest(
                        carPlate ,amount, selectedDate.toString()
                    )
                    onSelected(request)
                }
            ) {
                Text("Update damage")
            }
        }
    }
}
