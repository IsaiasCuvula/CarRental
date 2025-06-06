package com.bersyte.rent_a_car.features.customers.profile.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.customers.profile.data.User
import com.bersyte.rent_a_car.features.customers.profile.ui.components.AuthButtons
import com.bersyte.rent_a_car.features.customers.profile.ui.components.GuestMessage
import com.bersyte.rent_a_car.features.customers.profile.ui.components.ProfileHeader
import com.bersyte.rent_a_car.features.customers.profile.ui.components.RentalStatsSection
import com.bersyte.rent_a_car.features.customers.profile.ui.components.UserInfoSection

@Composable
fun ProfileScreen() {

    val user = User.sampleUser;


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader(user = user)

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
