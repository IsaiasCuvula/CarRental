package com.bersyte.rent_a_car.common.ui.components.car

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.customers.my_cars.data.models.CarStats
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.customers.my_cars.viewmodels.MyCarsViewModel
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@Composable
fun CarStatsSection(
    cars: List<Car>,
    viewModel: MyCarsViewModel = hiltViewModel()
) {
     var rentals by remember { mutableStateOf<List<Rental>>(listOf()) }
    val totalCarsInFleet = remember(cars) { cars.size }

    val context = LocalContext.current

    LaunchedEffect("fetchRentals") {
        viewModel.fetchRentals(
            onSuccess = { result ->
                rentals = result ?: listOf()
            },
            onError = { error ->
                AppHelpers.showToast(context,"$error")
            }
        )
    }

    val completedRentals = remember(rentals) {
        rentals.filter { it.status.equals("Completed", ignoreCase = true) }
    }

    val totalRevenue = remember(completedRentals) {
        completedRentals.sumOf { it.totalPaidAmount } / 100L
    }

    val activeRentals = remember(rentals) {
        rentals.count { it.status.equals("Active", ignoreCase = true) }
    }
    val utilizationRate = if (totalCarsInFleet > 0) {
        (activeRentals * 100) / totalCarsInFleet
    } else {
        0
    }

    val totalRentals = rentals.size
    val stats =   CarStats(
        totalCars = totalCarsInFleet,
        totalRevenue = totalRevenue,
        activeRentals = activeRentals,
        avgRating = 4.5,
        utilizationRate = utilizationRate,
        totalRentals = totalRentals
    )

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Your Fleet Stats",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItemCar(value = stats.totalCars.toString(), label = "Total Cars")
                StatItemCar(value = "$${stats.totalRevenue}", label = "Total Revenue")
                StatItemCar(value = stats.activeRentals.toString(), label = "Active Rentals")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItemCar(value = "${stats.avgRating}★", label = "Avg Rating")
                StatItemCar(value = "${stats.utilizationRate}%", label = "Utilization")
                StatItemCar(value = stats.totalRentals.toString(), label = "All Rentals")
            }
        }
    }
}


@Composable
private fun StatItemCar(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = " $value",
            style = MaterialTheme.typography.titleLarge ,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
