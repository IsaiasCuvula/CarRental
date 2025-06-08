package com.bersyte.rent_a_car.features.customers.profile.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer

@Composable
fun ProfileManagementScreen(
    //viewModel: ProfileViewModel = viewModel()
) {
    var showEditScreen by remember { mutableStateOf(false) }
    val user = Customer.sampleUser

    if (showEditScreen && user != null) {
        ProfileUpdateScreen(
            currentUser = user,
            onUpdate = { updatedData ->
               // viewModel.updateProfile(updatedData)
                showEditScreen = false
            },
            onCancel = { showEditScreen = false }
        )
    } else {
        ProfileScreen(
            onEditClick = { showEditScreen = true }
        )
    }
}
