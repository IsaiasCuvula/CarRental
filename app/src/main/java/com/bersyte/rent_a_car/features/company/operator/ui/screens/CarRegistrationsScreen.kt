package com.bersyte.rent_a_car.features.company.operator.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.company.operator.data.models.CarRegistration
import com.bersyte.rent_a_car.features.company.operator.viewmodels.OperatorViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarRegistrationsScreen(
    onBack: () -> Unit,
    viewModel: OperatorViewModel = hiltViewModel()

) {
    var allCarRegistrations by remember { mutableStateOf<List<CarRegistration>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchAllRegistrations(
            onSuccess = { registrations ->
                allCarRegistrations = registrations
            },
            onError = { error ->
                AppHelpers.showToast(context, error)
            }
        )
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(allCarRegistrations) { registration ->
                CarRegistrationCard(
                    registration = registration,
                    onApprove = {  },
                    onReject = {  }
                )
            }
        }
    }
}
