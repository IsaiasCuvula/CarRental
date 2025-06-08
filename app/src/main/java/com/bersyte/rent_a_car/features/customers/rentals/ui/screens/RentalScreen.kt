package com.bersyte.rent_a_car.features.customers.rentals.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.ui.components.ScrollableFilterChips
import com.bersyte.rent_a_car.features.customers.rentals.ui.components.RentalCard
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.customers.rentals.viewmodels.RentalViewModel
import com.bersyte.rent_a_car.utils.enums.RentalStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalScreen(
    viewModel: RentalViewModel = hiltViewModel()
) {

    val rentals = viewModel.rentals
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    val rentalStatusOptions = RentalStatus.entries.map { it.name }
    var selectedFilterIndex by remember { mutableIntStateOf(0) }

    val filteredRentals = remember(rentals, selectedFilterIndex) {
        if (selectedFilterIndex == 0) rentals else {
            rentals.filter { it.status == rentalStatusOptions[selectedFilterIndex] }
        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =  MaterialTheme.colorScheme.primary
                ),
                title = { Text("My rentals", color = Color.White)}
            )
        }
    ){ innerPadding ->

        when {
            isLoading -> CircularProgressIndicator()
            error != null -> Text("Error: $error")
            else -> Column(
                modifier = Modifier.fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(16.dp)
            ) {
                // Filter chips
                ScrollableFilterChips(
                    options = rentalStatusOptions,
                    selectedIndex = selectedFilterIndex,
                    onSelected = { selectedFilterIndex = it }
                )

                // Rental list
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical =  16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredRentals) { rental ->
                        RentalCard(rental = rental)
                    }

                    if (filteredRentals.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No rentals found",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}
