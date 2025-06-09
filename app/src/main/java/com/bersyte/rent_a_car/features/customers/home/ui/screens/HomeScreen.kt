package com.bersyte.rent_a_car.features.customers.home.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.customers.home.ui.components.CarCard
import com.bersyte.rent_a_car.features.customers.home.ui.components.HomeSearchBar
import com.bersyte.rent_a_car.common.ui.components.ScrollableFilterChips
import com.bersyte.rent_a_car.features.customers.home.ui.components.ReservationDateDialog
import com.bersyte.rent_a_car.features.customers.home.ui.components.ShowDatePickerDialog
import com.bersyte.rent_a_car.features.customers.home.viewmodels.HomeViewModel
import com.bersyte.rent_a_car.utils.enums.CarType
import androidx.compose.material3.TopAppBar as TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    // Filter chips
    val filterOptions = CarType.getAllFilterOptions()
    var selectedFilter by remember { mutableIntStateOf(0) }

    var searchQuery by remember { mutableStateOf("") }
    val cars by viewModel.cars.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAvailableCars()
    }

    // Add these state variables to your HomeScreen
    var selectedCar by remember { mutableStateOf<Car?>(null) }
    var showDateDialog by remember { mutableStateOf(false) }


    val filteredCars = remember(cars, selectedFilter, searchQuery) {
        // First filter by type if something other than "All" is selected
        val typeFilteredCars = if (selectedFilter == 0) {
            cars
        } else {
            val selectedType = CarType.fromDisplayName(filterOptions[selectedFilter])
            cars.filter { car -> CarType.valueOf(car.carType) == selectedType }
        }

        // Then apply search filter
        if (searchQuery.isEmpty()) {
            typeFilteredCars
        } else {
            typeFilteredCars.filter { car ->
                car.name.contains(searchQuery, ignoreCase = true) ||
                        car.model.contains(searchQuery, ignoreCase = true) ||
                        car.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }

        Scaffold (
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor =  MaterialTheme.colorScheme.primary
                    ),
                    title = { Text("Find Your Perfect Ride", color = Color.White)}
                )
            }
        ){ innerPadding ->

            Column(
                modifier = Modifier.fillMaxSize()
                 .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp)
            ) {
                // Search bar
                HomeSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = { /* Handle search */ },
                )

                Spacer(modifier = Modifier.height(8.dp))

                ScrollableFilterChips(
                    options = filterOptions,
                    selectedIndex = selectedFilter,
                    onSelected = { selectedFilter = it }
                )

                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(top = 8.dp)
                    ) {
                        items(filteredCars) { car ->
                            CarCard(
                                car = car,
                                onClick = { selectedCar = car }
                            )
                        }
                    }
                }

                // Show bottom sheet when car is selected
                selectedCar?.let { car ->
                    CarDetailsBottomSheet(
                        car = car,
                        onDismiss = { selectedCar = null },
                        onReserveClick = { showDateDialog = true }
                    )
                }
            }
        }

    // Show date selection dialog
    if (showDateDialog) {
        ReservationDateDialog(
            onDismiss = { showDateDialog = false },
            onDatesSelected = { start, end ->
                viewModel.reserveCar(selectedCar!!.plate, start.toString(),
                    end.toString()
                )
                showDateDialog = false
                selectedCar = null
            }
        )
    }
}
