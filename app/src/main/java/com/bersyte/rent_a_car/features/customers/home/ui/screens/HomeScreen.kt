package com.bersyte.rent_a_car.features.customers.home.ui.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.customers.home.ui.components.CarCard
import com.bersyte.rent_a_car.common.ui.components.CommonSearchBar
import com.bersyte.rent_a_car.common.ui.components.ScrollableFilterChips
import com.bersyte.rent_a_car.common.ui.components.VerticalSpace
import com.bersyte.rent_a_car.features.customers.home.data.models.ReservationRequest
import com.bersyte.rent_a_car.features.customers.home.ui.components.ReservationDateBottomSheet
import com.bersyte.rent_a_car.features.customers.home.viewmodels.HomeViewModel
import com.bersyte.rent_a_car.utils.enums.CarClass
import com.bersyte.rent_a_car.utils.enums.CarStatus
import com.bersyte.rent_a_car.utils.enums.CarType
import com.bersyte.rent_a_car.utils.enums.FuelType
import com.bersyte.rent_a_car.utils.helpers.AppHelpers
import java.time.format.DateTimeFormatter
import androidx.compose.material3.TopAppBar as TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val allCars by viewModel.cars.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var selectedCar by remember { mutableStateOf<Car?>(null) }
    var showDateDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.loadAvailableCars()
    }

    val filterCarTypeOptions = CarType.getAllFilterOptions()
    var selectedCarType by remember { mutableIntStateOf(0)}
    var searchQuery by remember { mutableStateOf("") }

    val filterCarClassOptions = CarClass.entries.map { it.name }
    val filterFuelTypeOptions = FuelType.entries.map { it.displayName }

    var selectedCarClass by remember { mutableStateOf<CarClass?>(null) }
    var selectedFuelType by remember { mutableStateOf<FuelType?>(null) }

    val years = listOf("All") + (2004..2030).map { it.toString() }
    var selectedYear by remember { mutableStateOf<String?>("All") }


    val filteredCars = remember(
        allCars, selectedCarType, searchQuery,
        selectedCarClass, selectedFuelType, selectedYear,
    ) {
        val typeFilteredCars = if (selectedCarType == 0) {
            allCars
        } else {
            val selectedType = CarType.fromDisplayName(filterCarTypeOptions[selectedCarType])
            allCars.filter { car -> CarType.valueOf(car.carType) == selectedType }
        }

        val classFilteredCars = selectedCarClass?.let { carClass ->
            typeFilteredCars.filter { it.carClass == carClass.name }
        } ?: typeFilteredCars

        val fuelFilteredCars = selectedFuelType?.let { fuelType ->
            classFilteredCars.filter { it.fuelType == fuelType.name }
        } ?: classFilteredCars

        val yearFilteredCars = selectedYear?.toIntOrNull()?.let { year ->
            fuelFilteredCars.filter { it.year == year }
        } ?: fuelFilteredCars

        if (searchQuery.isEmpty()) {
            yearFilteredCars
        } else {
            yearFilteredCars.filter { car ->
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
                CommonSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = { focusManager.clearFocus() },
                    hintText = "Search cars by model or type"
                )

                Spacer(modifier = Modifier.height(8.dp))

                VerticalSpace()
                ScrollableFilterChips(
                    options = years,
                    selectedIndex = years.indexOf(selectedYear),
                    onSelected = { index ->
                        val option = years[index]
                        selectedYear = if (selectedYear == option) null else option
                    }
                )
                ScrollableFilterChips(
                    options = filterCarTypeOptions,
                    selectedIndex = selectedCarType,
                    onSelected = { selectedCarType = it }
                )
                ScrollableFilterChips(
                    options = filterFuelTypeOptions,
                    selectedIndex = filterFuelTypeOptions.indexOf(selectedFuelType?.displayName),
                    onSelected = { index ->
                        val option = filterFuelTypeOptions[index]
                        selectedFuelType = if (selectedFuelType?.displayName == option) null
                        else FuelType.entries.first { it.displayName == option }
                    }
                )

                ScrollableFilterChips(
                    options = filterCarClassOptions,
                    selectedIndex = filterCarClassOptions.indexOf(selectedCarClass?.name),
                    onSelected = { index ->
                        val option = filterCarClassOptions[index]
                        selectedCarClass = if (selectedCarClass?.name == option) null else CarClass.valueOf(option)
                    }
                )

                VerticalSpace()
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
        ReservationDateBottomSheet(
            onDismiss = { showDateDialog = false },
            onDatesSelected = { start, end ->
                val plate = selectedCar?.plate
                if(plate != null){
                    if(end.isAfter(start)){
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                        val startFormatted = start.format(formatter)
                        val endFormatted = end.format(formatter)

                        val request = ReservationRequest(
                            plate, startFormatted, endFormatted, false
                        )
                        viewModel.reserveCar(request, onResult = { rental ->
                            if(rental != null){
                                AppHelpers.showToast(context,"Reservation made successfully")
                                showDateDialog = false
                                selectedCar = null
                            }else{
                                AppHelpers.showToast(context,"Something went wrong")
                            }
                        } )
                    } else{
                        AppHelpers.showToast(context,"End date must be after start date!")
                    }
                }

            }
        )
    }
}
