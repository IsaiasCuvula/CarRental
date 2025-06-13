package com.bersyte.rent_a_car.features.company.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.company.data.models.UpdateCarStatus
import com.bersyte.rent_a_car.features.company.viewmodels.CompanyViewModel
import com.bersyte.rent_a_car.utils.enums.CarStatus
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCarStatusBottomSheet(
    car: Car,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    viewModel: CompanyViewModel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState()
    val oldStatus = CarStatus.valueOf(car.carStatus)
    var newCarStatus by remember { mutableStateOf(oldStatus) }

    val isLoading by viewModel.isLoading.collectAsState()
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CarStatusSelector(
                selectedStatus = newCarStatus.displayName,
                onStatusSelected = { newCarStatus = it },
                modifier = Modifier.fillMaxWidth(),
            )
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val request = UpdateCarStatus(
                            newCarStatus.name, car.plate
                        )
                        viewModel.updateCarStatus(
                            request,
                            onSuccess = {result ->
                                if (result != null) {
                                    AppHelpers.showToast(context, "Update successfully")
                                    onDismissRequest()
                                }
                            },
                            onError = {error ->
                                AppHelpers.showToast(context, "Something went wrong.\n$error")
                            }
                        )
                    },
                    enabled = newCarStatus != oldStatus
                ) {
                    Text("Update Status")
                }
            }
        }
    }
}
