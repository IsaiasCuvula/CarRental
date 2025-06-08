package com.bersyte.rent_a_car.features.customers.home.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.customers.home.viewmodels.HomeViewModel

@Composable
fun TotalCarRentals(
    plate: String,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var totalRentals by remember { mutableIntStateOf(0) }

    LaunchedEffect(plate) {
        viewModel.fetchTotalRentals(plate) { total ->
            if(total != null){
                totalRentals = total
            }
        }
    }

    InfoChip(
        icon = Icons.Default.Verified,
        text = "$totalRentals rentals"
    )
}
