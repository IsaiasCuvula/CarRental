package com.bersyte.rent_a_car.common.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.bersyte.rent_a_car.features.customers.my_cars.ui.components.CarClassSelector
import com.bersyte.rent_a_car.features.customers.my_cars.ui.components.CarSeatsSelector
import com.bersyte.rent_a_car.features.customers.my_cars.ui.components.CarTypeSelector
import com.bersyte.rent_a_car.features.customers.my_cars.ui.components.ColorPicker
import com.bersyte.rent_a_car.features.customers.my_cars.ui.components.FuelTypeSelector
import com.bersyte.rent_a_car.features.customers.my_cars.ui.components.SmokingAllowedToggle
import com.bersyte.rent_a_car.features.customers.my_cars.ui.components.YearSelector
import com.bersyte.rent_a_car.utils.helpers.AppHelpers.validateAndSave
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarScreen(
    onSave: (CarRequest) -> Unit,
    onCancel: () -> Unit
) {
    val yearNow = LocalDateTime.now().year

    var name by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var plate by remember { mutableStateOf("") }
    var selectedSeats by remember { mutableStateOf("") }
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
    var selectedColor by remember { mutableIntStateOf(Color.Red.toArgb()) }
    var selectedYear by remember { mutableStateOf(yearNow.toString()) }

    val scrollState = rememberScrollState()

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current;

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Car Name") },
                    modifier = Modifier.weight(2f),
                    maxLines = 1,
                )

                YearSelector(
                    selectedYear = selectedYear,
                    modifier = Modifier.weight(1f),
                    onYearSelected = { year -> selectedYear = year }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = mileage,
                    onValueChange = { mileage = it },
                    label = { Text("Mileage") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    maxLines = 1,
                )

                OutlinedTextField(
                    value = plate,
                    onValueChange = { plate = it },
                    label = { Text("License Plate") },
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it },
                    label = { Text("Model") },
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                )

                OutlinedTextField(
                    value = hourlyPrice,
                    onValueChange = { hourlyPrice = it },
                    label = { Text("Hourly Price") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = { Text("$") },
                    maxLines = 1,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FuelTypeSelector(
                    selectedFuel = fuelType,
                    onFuelSelected = { fuelType = it },
                    modifier = Modifier.weight(1f),
                )

                CarSeatsSelector(
                    selectedSeats = selectedSeats,
                    modifier = Modifier.weight(1f),
                    onSeatsSelected = { seats -> selectedSeats = seats }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CarClassSelector(
                    selectedClass = carClass,
                    onClassSelected = { carClass = it },
                    modifier = Modifier.weight(1f),
                )

                CarTypeSelector(
                    selectedType = carType,
                    onTypeSelected = { carType = it },
                    modifier = Modifier.weight(1f),
                )
            }

            Text(
                text = "Location",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            OutlinedTextField(
                value = street,
                onValueChange = { street = it },
                label = { Text("Street Address") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = cityName,
                    onValueChange = { cityName = it },
                    label = { Text("City") },
                    modifier = Modifier.weight(1f),
                    maxLines = 1,

                )

                OutlinedTextField(
                    value = state,
                    onValueChange = { state = it },
                    label = { Text("State") },
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                )
            }

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3,
            )

            SmokingAllowedToggle(
                isSmokingAllowed = smokingAllowed,
                onSmokingAllowedChanged = { smokingAllowed = it },
                modifier = Modifier.fillMaxWidth()
            )

            ColorPicker(
                initialColor = Color(selectedColor),
                onColorSelected = { colorInt ->
                    selectedColor = colorInt
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                 onClick = {
                     focusManager.clearFocus()
                     validateAndSave(
                         context = context,
                         name = name,
                         model = model,
                         plate = plate,
                         selectedSeats = selectedSeats,
                         hourlyPrice = hourlyPrice,
                         carClass = carClass,
                         carType = carType,
                         fuelType = fuelType,
                         smokingAllowed = smokingAllowed,
                         mileage = mileage,
                         description = description,
                         cityName = cityName,
                         street = street,
                         state = state,
                         selectedColor = selectedColor,
                         selectedYear = selectedYear
                     )?.let { carRequest ->
                         onSave(carRequest)
                     }
                 },
            ){ Text("Save Car")}
        }
    }

}
