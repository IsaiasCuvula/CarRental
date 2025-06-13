package com.bersyte.rent_a_car.features.company.ui.components.damage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.ui.components.ShowDatePickerDialog
import com.bersyte.rent_a_car.common.ui.components.VerticalSpace
import com.bersyte.rent_a_car.features.company.data.models.UpdateDamageRequest
import com.bersyte.rent_a_car.features.company.data.models.VehicleDamage
import com.bersyte.rent_a_car.features.company.viewmodels.VehicleDamagesViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDamageBottomSheet(
    damage: VehicleDamage,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    viewModel: VehicleDamagesViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    var selectedDate by remember { mutableStateOf(LocalDateTime.now()) }
    var showDatePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
                text = "Select the car damage fix date",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Selected Date: ${selectedDate.toLocalDate()}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(onClick = { showDatePicker = true }) {
                    Text("Pick Date")
                }
            }
            VerticalSpace()
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val request = UpdateDamageRequest(
                        damage.carPlate , selectedDate.toString()
                    )
                    viewModel.updateDamage(
                        request,
                        onSuccess = {vehicleDamage ->
                            if (vehicleDamage != null) {
                                AppHelpers.showToast(context, "Update successfully")
                                showDatePicker = false
                            }
                        },
                        onError = {error ->
                            AppHelpers.showToast(context, "Something went wrong.\n$error")
                        }
                    )
                }
            ) {
                Text("Update damage")
            }
        }
    }

    if (showDatePicker) {
        ShowDatePickerDialog(
            onDismiss = { showDatePicker = false },
            onDateSelected = { dateLong ->
                dateLong?.let {
                    selectedDate = AppHelpers.longToLocalDateTime(it)
                }
            }
        )
    }
}
