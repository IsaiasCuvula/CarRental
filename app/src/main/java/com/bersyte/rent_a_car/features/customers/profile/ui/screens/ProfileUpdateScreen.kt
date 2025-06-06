package com.bersyte.rent_a_car.features.customers.profile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.customers.profile.data.UpdateCustomerRequest
import com.bersyte.rent_a_car.features.customers.profile.data.User
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.LocalDateTime

@Composable
fun ProfileUpdateScreen(
    currentUser: User,
    onUpdate: (UpdateCustomerRequest) -> Unit,
    onCancel: () -> Unit
) {
    // State for form fields
    var name by remember { mutableStateOf(currentUser.name) }
    var phone by remember { mutableStateOf(currentUser.phone) }
    var cityName by remember { mutableStateOf("") }
    var idCardNumber by remember { mutableStateOf("") }
    var driveLicense by remember { mutableStateOf("") }
    var driveLicenseExpirationDate by remember { mutableStateOf(LocalDateTime.now().plusYears(1)) }
    var street by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Update Profile",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Personal Information Section
        Text(
            text = "Personal Information",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        // Address Information Section
        Text(
            text = "Address Information",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = street,
            onValueChange = { street = it },
            label = { Text("Street Address") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("City") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            OutlinedTextField(
                value = state,
                onValueChange = { state = it },
                label = { Text("State") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        // Identification Section
        Text(
            text = "Identification",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = idCardNumber,
            onValueChange = { idCardNumber = it },
            label = { Text("ID Card Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = driveLicense,
            onValueChange = { driveLicense = it },
            label = { Text("Driver License Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "License Expiration: ${driveLicenseExpirationDate.format(dateFormatter)}",
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { /* Show date picker */ }) {
                Text("Change")
            }
        }

        // Action Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    val updateRequest = UpdateCustomerRequest(
                        name = name,
                        phone = phone,
                        cityName = cityName,
                        idCardNumber = idCardNumber,
                        driveLicense = driveLicense,
                        driveLicenseExpirationDate = driveLicenseExpirationDate,
                        street = street,
                        state = state
                    )
                    onUpdate(updateRequest)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Save Changes")
            }
        }
    }
}
