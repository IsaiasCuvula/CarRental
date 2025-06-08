package com.bersyte.rent_a_car.features.customers.my_cars.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.customers.home.ui.components.CarCard
import com.bersyte.rent_a_car.features.customers.my_cars.data.models.CarStats
import com.bersyte.rent_a_car.features.customers.my_cars.ui.components.CarStatsSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCarsScreen(
    onAddCarClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val cars = listOf<Car>()
    val stats = CarStats.stats

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =  MaterialTheme.colorScheme.primary
                ),
                title = { Text("My Cars", color = Color.White)}
            )
        },
        floatingActionButton = {
            FloatingActionButton (
                onClick = onAddCarClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Car")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
                .padding(16.dp)
                //.verticalScroll(rememberScrollState())
        ) {
            // Statistics Section
            CarStatsSection(stats = stats.first())

            Spacer(modifier = Modifier.height(16.dp))

            // Cars List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cars) { car ->
                    CarCard(car = car, onClick = {  })
                }
            }
        }
    }
}
