package com.bersyte.rent_a_car.features.customers.my_cars.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.customers.my_cars.viewmodels.MyCarsViewModel

@Composable
fun MyCarsManagementScreen(
    viewModel: MyCarsViewModel = hiltViewModel()
) {
    var showAddCarScreen by remember { mutableStateOf(false) }

    when {
        showAddCarScreen -> {
            AddCarScreen(
                onSave = { carRequest ->
                    viewModel.saveCar(carRequest)
                    showAddCarScreen = false
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
