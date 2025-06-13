package com.bersyte.rent_a_car.features.company.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.utils.enums.CarStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCarStatusBottomSheet(
    car: Car,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {

    val sheetState = rememberModalBottomSheetState()
    val oldStatus = CarStatus.valueOf(car.carStatus)
    var carStatus by remember { mutableStateOf(oldStatus) }


    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CarStatusSelector(
                selectedStatus = carStatus.displayName,
                onStatusSelected = { carStatus = it },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
//                    val request = UpdateDamageRequest(
//                        damage.carPlate , selectedDate.toString()
//                    )
//                    viewModel.updateDamage(
//                        request,
//                        onSuccess = {vehicleDamage ->
//                            if (vehicleDamage != null) {
//                                AppHelpers.showToast(context, "Update successfully")
//                                showDatePicker = false
//                            }
//                        },
//                        onError = {error ->
//                            AppHelpers.showToast(context, "Something went wrong.\n$error")
//                        }
//                    )
                }
            ) {
                Text("Update Status")
            }
        }
    }
}
