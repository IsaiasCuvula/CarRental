package com.bersyte.rent_a_car.features.admin.dashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.admin.dashboard.data.AdminDashboardStats

@Composable
fun DashboardTab(stats: AdminDashboardStats) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item { StatCard("Total Users", stats.totalUsers.toString(), Icons.Default.People) }
            item { StatCard("Total Cars", stats.totalCars.toString(), Icons.Default.DirectionsCar) }
            item { StatCard("Active Rentals", stats.activeRentals.toString(), Icons.Default.EventAvailable) }
            item { StatCard("Available Cars", stats.availableCars.toString(), Icons.Default.CarRental) }
            item { StatCard("30-Day Revenue", "$${stats.revenueLast30Days}", Icons.Default.AttachMoney) }
            item { StatCard("New Users (30d)", stats.newUsersLast30Days.toString(), Icons.Default.PersonAdd) }
        }

        Text(
            text = "Recent Activity",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
