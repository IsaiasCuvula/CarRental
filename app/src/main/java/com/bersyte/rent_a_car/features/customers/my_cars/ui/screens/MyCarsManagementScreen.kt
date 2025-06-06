package com.bersyte.rent_a_car.features.customers.my_cars.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MyCarsManagementScreen(
    //viewModel: MyCarsViewModel = viewModel()
) {
   // val uiState by viewModel.uiState.collectAsState()
    var showAddCarScreen by remember { mutableStateOf(false) }

    when {
        showAddCarScreen -> {
            AddCarScreen(
                onSave = { carRequest ->
//                    viewModel.addCar(carRequest)
                    showAddCarScreen = false
                },
                onCancel = { showAddCarScreen = false }
            )
        }
        else -> {
            MyCarsScreen(
//                cars = uiState.cars,
//                stats = uiState.stats,
//                onCarClick = { car ->
//                    // Navigate to car details
//                },
                onAddCarClick = { showAddCarScreen = true }
            )
        }
    }
}
