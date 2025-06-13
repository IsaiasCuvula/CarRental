package com.bersyte.rent_a_car.features.customers.profile.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bersyte.rent_a_car.features.customers.profile.viewmodels.ProfileViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@Composable
fun ProfileManagementScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    var showEditScreen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchCustomer()
    }

    val customerState = viewModel.customer.collectAsState()
    val customer = customerState.value

    if (showEditScreen && customer != null) {
        ProfileUpdateScreen(
            currentUser = customer,
            onUpdate = { updatedData ->
                viewModel.updateCustomer(
                    updatedData,
                    onSuccess = { result ->
                        if(result != null){
                            AppHelpers.showToast(context, "Updated Successfully")
                            showEditScreen = false
                        }
                    },
                    onError = {
                        error -> AppHelpers.showToast(context, error)
                    },
                )

            },
            onCancel = { showEditScreen = false }
        )
    } else {
        ProfileScreen(
            customer,
            onEditClick = { showEditScreen = true },
            navController,
        )
    }
}
