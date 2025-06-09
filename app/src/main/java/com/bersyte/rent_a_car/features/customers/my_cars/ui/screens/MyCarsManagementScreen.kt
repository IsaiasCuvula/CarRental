package com.bersyte.rent_a_car.features.customers.my_cars.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.customers.my_cars.viewmodels.MyCarsViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@Composable
fun MyCarsManagementScreen(
    viewModel: MyCarsViewModel = hiltViewModel()
) {
    var showAddCarScreen by remember { mutableStateOf(false) }

    val context = LocalContext.current

    when {
        showAddCarScreen -> {
            AddCarScreen(
                onSave = { carRequest ->
                    viewModel.registerCar(
                        carRequest,
                        onSuccess = {result ->
                            if(result != null){
                                showAddCarScreen = false
                            }
                        },
                        onError = {error ->
                            AppHelpers.showToast(context, "$error")
                        }
                    )

                },
                onCancel = { showAddCarScreen = false }
            )
        }
        else -> {
            MyCarsScreen(
                onAddCarClick = { showAddCarScreen = true }
            )
        }
    }
}
