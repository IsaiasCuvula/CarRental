package com.bersyte.rent_a_car.features.customers.profile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.features.customers.profile.ui.components.AuthButtons
import com.bersyte.rent_a_car.features.customers.profile.ui.components.GuestMessage
import com.bersyte.rent_a_car.features.customers.profile.ui.components.ProfileHeader
import com.bersyte.rent_a_car.features.customers.profile.ui.components.RentalStatsSection
import com.bersyte.rent_a_car.features.customers.profile.ui.components.UserInfoSection

@Composable
fun ProfileScreen( onEditClick: () -> Unit) {

    val user = Customer.sampleUser


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            ProfileHeader(user = user)

            if (user != null) {
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Profile",
                        tint = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (user != null) {
            UserInfoSection(user)
            Spacer(modifier = Modifier.height(12.dp))
            RentalStatsSection()
            Spacer(modifier = Modifier.height(16.dp))
        }

        AuthButtons(
            isLoggedIn = user != null,
            onLoginClick = {},
            onLogoutClick = {}
        )

        if (user == null) {
            GuestMessage()
        }
    }

}
