package com.bersyte.rent_a_car.features.customers.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@Composable
 fun UserInfoSection(user: Customer) {

    val driverLicenseExpirationDate = user.driverLicenseExpirationDate?.let {
        AppHelpers.formatDateOnly(it)
    } ?: ""

    val idCard = user.idCardNumber ?: ""
    val driveLicense = user.driverLicenseNumber ?: ""

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoRow(icon = Icons.Default.Email, text = user.email)
            user.phone?.let { InfoRow(icon = Icons.Default.Phone, text = it) }
            InfoRow(icon = Icons.Default.Badge, text = "ID: $idCard")
            InfoRow(icon = Icons.Default.Badge, text = "Drive License: $driveLicense")
            InfoRow(icon = Icons.Default.CalendarMonth, text = "Expires at: $driverLicenseExpirationDate")
        }
    }
}
