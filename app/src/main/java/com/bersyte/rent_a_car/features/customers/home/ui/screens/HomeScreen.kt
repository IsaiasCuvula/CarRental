package com.bersyte.rent_a_car.features.customers.home.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.data.models.Address
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRating
import com.bersyte.rent_a_car.features.customers.home.ui.components.CarCard
import com.bersyte.rent_a_car.features.customers.home.ui.components.HomeSearchBar
import com.bersyte.rent_a_car.features.customers.home.ui.components.ScrollableFilterChips
import java.time.LocalDateTime

@Composable
fun HomeScreen() {

    var searchQuery by remember { mutableStateOf("") }
    val cars = listOf<Car>(
        Car(
            color = 0,
            smokingAllowed = false,
            seats = 5,
            discountPercentage = 10,
            hourlyPrice = 25,
            feePerHourRented = 2,
            carClass = "Standard",
            carType = "Sedan",
            carStatus = "Available",
            fuelType = "Gasoline",
            photos = "car1.jpg",
            name = "Comfort Plus",
            description = "A comfortable sedan with great mileage and modern features.",
            model = "Toyota Camry",
            year = 2022,
            plate = "ABC123",
            address = Address("123 Main St", "New York", "NY"),
            ratings = listOf(CarRating(4.5), CarRating(5.0), CarRating(4.0)),
            createdAt = LocalDateTime.now()
        ),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Text(
            text = "Find Your Perfect Ride",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        // Search bar
        HomeSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = { /* Handle search */ },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Filter chips
        val filterOptions = listOf("All", "Economy", "Luxury", "SUV", "Electric")
        var selectedFilter by remember { mutableIntStateOf(0) }

        ScrollableFilterChips(
            options = filterOptions,
            selectedIndex = selectedFilter,
            onSelected = { selectedFilter = it }
        )

        // Cars list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(cars) { car ->
                CarCard(
                    car = car,
                    onClick = {  }
                )
            }
        }
    }
}
