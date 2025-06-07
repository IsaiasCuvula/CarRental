package com.bersyte.rent_a_car.features.customers.my_cars.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.data.models.CarRequest
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarScreen(
    onSave: (CarRequest) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var plate by remember { mutableStateOf("") }
    var seats by remember { mutableStateOf("") }
    var color by remember { mutableIntStateOf(0) }
    var hourlyPrice by remember { mutableStateOf("") }
    var carClass by remember { mutableStateOf("") }
    var carType by remember { mutableStateOf("") }
    var fuelType by remember { mutableStateOf("") }
    var smokingAllowed by remember { mutableStateOf(false) }
    var mileage by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var cityName by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
              TopAppBar(
                   colors = TopAppBarDefaults.topAppBarColors(
                       containerColor =  MaterialTheme.colorScheme.primary
                   ),
                   title = { Text("Add New Car", color = Color.White)},
                   navigationIcon = {
                       IconButton(onClick = onCancel) {
                           Icon(
                               Icons.AutoMirrored.Filled.ArrowBack,
                               contentDescription = "Back",
                               tint = Color.White
                           )
                       }
                   },
              )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    onSave(
                        CarRequest(
                            color = color,
                            smokingAllowed = smokingAllowed,
                            seats = seats.toIntOrNull() ?: 0,
                            hourlyPrice = hourlyPrice.toLongOrNull() ?: 0,
                            carClass = carClass,
                            carType = carType,
                            fuelType = fuelType,
                            name = name,
                            description = description,
                            model = model,
                            year = year.toIntOrNull() ?: 0,
                            plate = plate,
                            mileage = mileage.toLongOrNull() ?: 0,
                            cityName = cityName,
                            street = street,
                            state = state
                        )
                    )
                },
                icon = { Icon(Icons.Default.Done, contentDescription = "Save") },
                text = { Text("Save Car") },
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Car Information",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Car Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = model,
                onValueChange = { model = it },
                label = { Text("Model") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = year,
                    onValueChange = { if (it.length <= 4) year = it },
                    label = { Text("Year") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = plate,
                    onValueChange = { plate = it },
                    label = { Text("License Plate") },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = seats,
                    onValueChange = { seats = it },
                    label = { Text("Seats") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = hourlyPrice,
                    onValueChange = { hourlyPrice = it },
                    label = { Text("Hourly Price") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = { Text("$") }
                )
            }

            // More fields for car class, type, fuel, etc.
            // ... (similar pattern as above fields)

            Text(
                text = "Location",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = street,
                onValueChange = { street = it },
                label = { Text("Street Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = cityName,
                    onValueChange = { cityName = it },
                    label = { Text("City") },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = state,
                    onValueChange = { state = it },
                    label = { Text("State") },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
