package com.bersyte.rent_a_car.features.company.operator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.ui.components.ScrollableFilterChips
import com.bersyte.rent_a_car.features.company.operator.data.models.RentingCarRequest
import com.bersyte.rent_a_car.features.company.operator.ui.components.EnterInitialConditions
import com.bersyte.rent_a_car.features.company.operator.ui.components.RentalRequestCard
import com.bersyte.rent_a_car.features.company.operator.viewmodels.OperatorViewModel
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.utils.enums.RentalStatus
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalManagementScreen(
    onBack: () -> Unit,
    viewModel: OperatorViewModel = hiltViewModel()
) {

    val rentalStatusOptions = RentalStatus.entries.map { it.name }
    var selectedFilterIndex by remember { mutableIntStateOf(0) }

    var showBottomSheet by remember { mutableStateOf<Rental?>(null) }

    val context = LocalContext.current

    val allRentalsState= viewModel.rentals.collectAsState()
    val rentals = allRentalsState.value

    val filteredRentals = remember(rentals, selectedFilterIndex) {
        if (selectedFilterIndex == 0) rentals else {
            rentals.filter { it.status == rentalStatusOptions[selectedFilterIndex] }
        }
    }


    LaunchedEffect(Unit) {
        viewModel.fetchAllRentals(
            onError = { error ->
                AppHelpers.showToast(context, error)
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Rental Requests") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
       Column(
           modifier = Modifier
               .padding(innerPadding)
               .fillMaxSize()
               .padding(16.dp),
       ) {
           ScrollableFilterChips(
               options = rentalStatusOptions,
               selectedIndex = selectedFilterIndex,
               onSelected = { selectedFilterIndex = it }
           )

           LazyColumn(
               verticalArrangement = Arrangement.spacedBy(16.dp)
           ) {
               items(filteredRentals) { rental ->
                   RentalRequestCard(
                       rental = rental,
                       onApprove = {showBottomSheet = rental},
                       onReject = {
                           viewModel.cancelRenting(
                               rentalCode = rental.rentalCode, onSuccess = { data ->
                                   if(data != null){
                                       AppHelpers.showToast(context, "Rental cancelled successfully")
                                   }
                               },
                               onError = {error->
                                   if (error != null) {
                                       AppHelpers.showToast(context, error)
                                   }
                               }
                           )
                       },
                       onFinalize = {}
                   )
               }
           }
       }
    }

    showBottomSheet?.let { rental ->
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = null }
        ) {
            EnterInitialConditions { initialCondition ->
                showBottomSheet = null
                val request = RentingCarRequest(
                    rental.rentalCode, initialCondition = initialCondition,
                )
                viewModel.approveRental(
                    request, onSuccess = { data ->
                        if(data != null){
                            AppHelpers.showToast(context, "Rental approved successfully")
                        }
                    },
                    onError = {error->
                        if (error != null) {
                            AppHelpers.showToast(context, error)
                        }
                    }
                )

            }
        }
    }
}
