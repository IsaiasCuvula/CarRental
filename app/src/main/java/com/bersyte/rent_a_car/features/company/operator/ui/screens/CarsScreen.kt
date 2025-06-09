package com.bersyte.rent_a_car.features.company.operator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
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
import com.bersyte.rent_a_car.common.ui.components.AddCarScreen
import com.bersyte.rent_a_car.common.ui.components.CommonSearchBar
import com.bersyte.rent_a_car.common.ui.components.ScrollableFilterChips
import com.bersyte.rent_a_car.features.company.operator.viewmodels.OperatorViewModel
import com.bersyte.rent_a_car.features.customers.home.ui.components.CarCard
import com.bersyte.rent_a_car.utils.enums.CarType
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarsScreen(
    onCancel: () -> Unit,
    viewModel: OperatorViewModel = hiltViewModel()
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

    val filterOptions = CarType.getAllFilterOptions()
    var selectedFilter by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredCars = remember(allCars, selectedFilter, searchQuery) {
        // First filter by type if something other than "All" is selected
        val typeFilteredCars = if (selectedFilter == 0) {
            allCars
        } else {
            val selectedType = CarType.fromDisplayName(filterOptions[selectedFilter])
            allCars.filter { car -> CarType.valueOf(car.carType) == selectedType }
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CommonSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = { focusManager.clearFocus() },
                    hintText = "Search cars by model or type"
                )

                ScrollableFilterChips(
                    options = filterOptions,
                    selectedIndex = selectedFilter,
                    onSelected = { selectedFilter = it }
                )

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
