package com.bersyte.rent_a_car.features.customers.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer

@Composable
 fun UserInfoSection(user: Customer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoRow(icon = Icons.Default.Email, text = user.email)
            InfoRow(icon = Icons.Default.Phone, text = user.phone)
            InfoRow(icon = Icons.Default.Badge, text = "ID: ${user.idCardNumber}")
            InfoRow(icon = Icons.Default.Badge, text = "Drive License: ${user.driverLicenseNumber}")
            InfoRow(icon = Icons.Default.Badge, text = "Expires at: ${user.driverLicenseExpirationDate}")
        }
    }
}
