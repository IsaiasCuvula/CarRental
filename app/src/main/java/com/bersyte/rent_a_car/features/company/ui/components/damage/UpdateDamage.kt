package com.bersyte.rent_a_car.features.company.ui.components.damage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.company.data.models.FixDamageRequest
import com.bersyte.rent_a_car.features.company.data.models.VehicleDamage
import com.bersyte.rent_a_car.features.company.viewmodels.VehicleDamagesViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDamage(
    onDismissRequest: () -> Unit,
    damage: VehicleDamage,
    viewModel: VehicleDamagesViewModel = hiltViewModel()
) {
    var showAmountSheet by remember { mutableStateOf(false) }
    var showSimpleSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { showAmountSheet = true }) {
                Text("Show Amount & Date Sheet")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { showSimpleSheet = true }) {
                Text("Show Simple Date Sheet")
            }
        }
    }

    if (showAmountSheet) {
        PayDamageBottomSheet (
            onDismissRequest = { showAmountSheet = false },
            onDateSelected = { date ->
                println("Selected date: $date")
            },
            onAmountEntered = { amount ->
                println("Entered amount: $amount")
            }
        )
    }

    if (showSimpleSheet) {
        FixDamageBottomSheet(
            onDismissRequest = { showSimpleSheet = false },
            onDateSelected = { date ->
                val request = FixDamageRequest(
                    damage.carPlate,
                    date.toString()
                )
                viewModel.markDamageAsFixed(
                    request,
                    onError = {error->
                        AppHelpers.showToast(context, "Something went wrong\n$error")
                    },
                    onSuccess = { updatedDamage ->
                        if(updatedDamage != null){
                            showSimpleSheet = false
                            AppHelpers.showToast(context, "Updated successfully")
                            onDismissRequest()
                        }
                    }
                )
            }
        )
    }
}
