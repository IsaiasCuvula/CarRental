package com.bersyte.rent_a_car.features.customers.profile.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.customers.profile.viewmodels.ProfileViewModel

@Composable
fun ProfileManagementScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    var showEditScreen by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchCustomer()
        Log.d("ProfileManagementScreen", "ProfileManagementScreen")
    }

    val customerState = viewModel.customer.collectAsState()
    val customer = customerState.value

    if (showEditScreen && customer != null) {
        ProfileUpdateScreen(
            currentUser = customer,
            onUpdate = { updatedData ->
                //viewModel.updateCustomer(updatedData)
                showEditScreen = false
            },
            onCancel = { showEditScreen = false }
        )
    } else {
        ProfileScreen(
            customer,
            onEditClick = { showEditScreen = true }
        )
    }
}
