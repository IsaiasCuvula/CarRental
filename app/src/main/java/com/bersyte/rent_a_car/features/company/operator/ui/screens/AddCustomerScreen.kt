package com.bersyte.rent_a_car.features.company.operator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.util.UUID
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCustomerScreen(
    onSave: (Customer) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var idCardNumber by remember { mutableStateOf("") }
    var driveLicense by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add New Customer") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton (
                onClick = {
//                    onSave(
//                        Customer(
//                            id = UUID.randomUUID().toString(),
//                            name = name,
//                            email = email,
//                            phone = phone,
//                            joinDate = LocalDateTime.now(),
//                            idCardNumber = idCardNumber,
//                            driveLicense = driveLicense
//                        )
//                    )
                },
                icon = { Icon(Icons.Default.Save, contentDescription = "Save") },
                text = { Text("Save Customer") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            OutlinedTextField(
                value = idCardNumber,
                onValueChange = { idCardNumber = it },
                label = { Text("ID Card Number") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = driveLicense,
                onValueChange = { driveLicense = it },
                label = { Text("Driver License") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
