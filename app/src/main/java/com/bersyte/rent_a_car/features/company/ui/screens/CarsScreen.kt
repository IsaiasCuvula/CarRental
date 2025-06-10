package com.bersyte.rent_a_car.features.company.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.ui.screens.AddCarScreen
import com.bersyte.rent_a_car.common.ui.components.CommonSearchBar
import com.bersyte.rent_a_car.common.ui.components.ScrollableFilterChips
import com.bersyte.rent_a_car.common.ui.components.VerticalSpace
import com.bersyte.rent_a_car.features.company.ui.components.StatCard
import com.bersyte.rent_a_car.features.company.viewmodels.CompanyViewModel
import com.bersyte.rent_a_car.common.ui.components.CarCard
import com.bersyte.rent_a_car.utils.enums.CarClass
import com.bersyte.rent_a_car.utils.enums.CarStatus
import com.bersyte.rent_a_car.utils.enums.CarType
import com.bersyte.rent_a_car.utils.enums.FuelType
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarsScreen(
    onCancel: () -> Unit,
    viewModel: CompanyViewModel = hiltViewModel()
) {

    var showAddCarScreen by remember { mutableStateOf(false) }
    val allCarsState= viewModel.cars.collectAsState()
    val allCars = allCarsState.value
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.fetchAllCars(
           onError = { error ->
               AppHelpers.showToast(context, error)
           }
        )
    }

    val filterCarTypeOptions = CarType.getAllFilterOptions()
    var selectedCarType by remember { mutableIntStateOf(0)}
    var searchQuery by remember { mutableStateOf("") }

    val filterCarClassOptions = CarClass.entries.map { it.name }
    val filterFuelTypeOptions = FuelType.entries.map { it.displayName }

    val filterCarStatusOptions = CarStatus.entries.map { it.name }

    var selectedCarClass by remember { mutableStateOf<CarClass?>(null) }
    var selectedFuelType by remember { mutableStateOf<FuelType?>(null) }
    var selectedCarStatus by remember { mutableStateOf<CarStatus?>(null) }

    val years = listOf("All") + (2004..2030).map { it.toString() }
    var selectedYear by remember { mutableStateOf<String?>("All") }


    val filteredCars = remember(
        allCars, selectedCarType, searchQuery,
        selectedCarClass, selectedFuelType, selectedYear,
        selectedCarStatus,
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

        val statusFilteredCars = selectedCarStatus?.let { status ->
            fuelFilteredCars.filter { it.carStatus == status.name }
        } ?: fuelFilteredCars

        val yearFilteredCars = selectedYear?.toIntOrNull()?.let { year ->
            statusFilteredCars.filter { it.year == year }
        } ?: statusFilteredCars

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


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Cars") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddCarScreen = true},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add New Car")
            }
        },
        ){innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                CommonSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = { focusManager.clearFocus() },
                    hintText = "Search cars by model or type"
                )
                VerticalSpace()
                StatCard(
                    title = "",
                    value = "${allCars.size}",
                    icon = Icons.Default.ElectricCar,
                )
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
                    options = filterCarStatusOptions,
                    selectedIndex = filterCarStatusOptions.indexOf(selectedCarStatus?.name),
                    onSelected = { index ->
                        val option = filterCarStatusOptions[index]
                        selectedCarStatus = if (selectedCarStatus?.name == option) null else CarStatus.valueOf(option)
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

                LazyColumn(
                   modifier = Modifier.fillMaxSize(),
                   contentPadding = PaddingValues(top = 8.dp)
               ) {
                   items(filteredCars) { car ->
                       CarCard(
                           car = car,
                           onClick = {  }
                       )
                   }
               }
            }
    }
    if(showAddCarScreen){
        ModalBottomSheet(
            onDismissRequest = { showAddCarScreen = false },
            sheetState = rememberModalBottomSheetState()
        ) {
            AddCarScreen(
                onSave = { carRequest ->
                    viewModel.registerCar(
                        carRequest,
                        onSuccess = {result ->
                            if(result != null){
                                showAddCarScreen = false
                            }
                        },
                        onError = {error ->
                            AppHelpers.showToast(context, "$error")
                        }
                    )
                },
                onCancel = { showAddCarScreen = false }
            )
        }
    }
}
