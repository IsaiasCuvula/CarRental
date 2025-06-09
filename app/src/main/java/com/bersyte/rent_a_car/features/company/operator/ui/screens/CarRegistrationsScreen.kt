package com.bersyte.rent_a_car.features.company.operator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.company.operator.ui.components.CarRegistrationCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.ui.components.CommonSearchBar
import com.bersyte.rent_a_car.common.ui.components.ScrollableFilterChips
import com.bersyte.rent_a_car.features.company.operator.viewmodels.OperatorViewModel
import com.bersyte.rent_a_car.utils.enums.RegistrationStatus
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarRegistrationsScreen(
    onBack: () -> Unit,
    viewModel: OperatorViewModel = hiltViewModel()

) {
    val registrationsState= viewModel.registrations.collectAsState()
    val allRegistrations = registrationsState.value

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchAllRegistrations(
            onError = { error ->
                AppHelpers.showToast(context, error)
            }
        )
    }

    val filterOptions = RegistrationStatus.getAllFilterOptions()
    var selectedFilter by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current


    val filteredRegistrations = remember(allRegistrations, selectedFilter, searchQuery) {
        val statusFiltered = if (selectedFilter == 0) {
            allRegistrations
        } else {
            allRegistrations.filter {
                it.status.equals(filterOptions[selectedFilter], ignoreCase = true)
            }
        }

        if (searchQuery.isEmpty()) {
            statusFiltered
        } else {
            statusFiltered.filter { registration ->
                registration.registrationNumber.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Car Registrations") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            CommonSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { focusManager.clearFocus() },
                hintText = "Search registration by number"
            )


            ScrollableFilterChips(
                options = filterOptions,
                selectedIndex = selectedFilter,
                onSelected = { selectedFilter = it }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredRegistrations) { registration ->
                    val plate =registration.plate
                    val number = registration.registrationNumber

                    CarRegistrationCard(
                        registration = registration,
                        onApprove = {
                            if(plate != null){
                                viewModel.approveRegistration(
                                    plate = plate, registrationNumber = number, onSuccess = {},
                                    onError = {error->
                                        if (error != null) {
                                            AppHelpers.showToast(context, error)
                                        }
                                    }
                                )
                            }
                        },
                        onReject = {
                            if(plate != null){
                                viewModel.rejectRegistration(
                                    plate = plate, registrationNumber = number, onSuccess = {},
                                    onError = {error->
                                        if (error != null) {
                                            AppHelpers.showToast(context, error)
                                        }
                                    }
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
