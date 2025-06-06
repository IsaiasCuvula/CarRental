package com.bersyte.rent_a_car.features.customers.my_cars.ui.components

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
import com.bersyte.rent_a_car.features.customers.my_cars.data.CarStats
import com.bersyte.rent_a_car.features.customers.profile.ui.components.StatItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
fun CarStatsSection(stats: CarStats) {
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
                StatItem(value = stats.totalCars.toString(), label = "Total Cars")
                StatItem(value = "$${stats.totalRevenue}", label = "Total Revenue")
                StatItem(value = stats.activeRentals.toString(), label = "Active Rentals")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(value = "${stats.avgRating}★", label = "Avg Rating")
                StatItem(value = "${stats.utilizationRate}%", label = "Utilization")
                StatItem(value = stats.totalRentals.toString(), label = "All Rentals")
            }
        }
    }
}
