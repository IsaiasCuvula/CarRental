package com.bersyte.rent_a_car.features.customers.profile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.features.customers.rentals.viewmodels.RentalViewModel

@Composable
fun RentalStatsSection(
    customer: Customer,
    viewModel: RentalViewModel = hiltViewModel()
) {
    val rentals = viewModel.rentals

    val activeRentals = rentals.filter { it.status.equals("ACTIVE", ignoreCase = true) }
    val reservedRentals = rentals.filter { it.status.equals("RESERVED", ignoreCase = true) }
    val canceledRentals = rentals.filter { it.status.equals("CANCELLED", ignoreCase = true) }
    val completedRentals = rentals.filter { it.status.equals("COMPLETED", ignoreCase = true) }


    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Rental Stats",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                StatItem(value = "${rentals.size}", label = "Total Rentals")
                StatItem(value = "${reservedRentals.size}", label = "Reserved")
                StatItem(value = "${activeRentals.size}", label = "Active")
                StatItem(value = "${completedRentals.size}", label = "Completed")
                StatItem(value = "${canceledRentals.size}", label = "Cancelled")
                StatItem(value = "${customer.loyaltyPoints}", label = "Loyalty Points")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            )
            {
                StatItem(value = "${customer.loyaltyPoints}", label = "Loyalty Points")
            }
        }
    }
}
