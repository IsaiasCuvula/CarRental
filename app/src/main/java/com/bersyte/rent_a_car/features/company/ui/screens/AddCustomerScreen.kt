package com.bersyte.rent_a_car.features.company.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import com.bersyte.rent_a_car.common.ui.components.CommonTextField
import com.bersyte.rent_a_car.features.company.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.common.ui.components.ShowDatePickerDialog
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCustomerScreen(
    onSave: (CreateCustomerRequest) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var idCardNumber by remember { mutableStateOf("") }
    var driveLicense by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }

    var driveLicenseExpirationDate by remember {
        mutableStateOf(LocalDateTime.now().plusYears(1))
    }

    var showDatePicker by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    fun validate(): Boolean {
        val isNameValid = name.isNotBlank()
        val isEmailValid = email.isNotBlank()
        val isPhoneValid =phone.isNotBlank()
        val isPasswordValid = password.isNotBlank()
        val isIdCardValid =  idCardNumber.isNotBlank()

        val isCityValid =  city.isNotBlank()
        val isStreetValid =  state.isNotBlank()
        val isStateValid =  street.isNotBlank()

        val isDriveLicenseValid = driveLicense.isNotBlank()
        val isDriveLicenseExpirationValid =  driveLicenseExpirationDate.isAfter(LocalDateTime.now())

        return isEmailValid && isPasswordValid && isCityValid &&
                isStreetValid && isStateValid &&
                isNameValid && isPhoneValid && isIdCardValid &&
                isDriveLicenseValid && isDriveLicenseExpirationValid
    }


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
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    CommonTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = "Full Name",
                        icon = Icons.Default.Person,
                        isError = name.isBlank()
                    )

                    CommonTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        icon = Icons.Default.Mail,
                        isError = email.isBlank(),
                        keyboardType =  KeyboardType.Email
                    )

                    CommonTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        icon = Icons.Default.Lock,
                        keyboardType = KeyboardType.Password,
                        isPassword = true,
                        isError = password.isBlank()
                    )

                    CommonTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = "Phone",
                        icon = Icons.Default.Phone,
                        isError = phone.isBlank(),
                        keyboardType = KeyboardType.Phone
                    )

                    CommonTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = "City",
                        icon = Icons.Default.LocationCity,
                        isError = city.isBlank(),
                    )

                    CommonTextField(
                        value = state,
                        onValueChange = { state = it },
                        label = "State",
                        icon = Icons.Default.LocationCity,
                        isError = state.isBlank(),
                    )

                    CommonTextField(
                        value = street,
                        onValueChange = { street = it },
                        label = "Street",
                        icon = Icons.Default.Home,
                        isError = street.isBlank(),
                    )

                    CommonTextField(
                        value = driveLicense,
                        onValueChange = { driveLicense = it },
                        label = "Driver License",
                        icon = Icons.Default.Badge,
                        isError = driveLicense.isBlank(),
                    )

                    CommonTextField(
                        value = idCardNumber,
                        onValueChange = { idCardNumber = it },
                        label = "ID Card Number",
                        icon = Icons.Default.Badge,
                        isError = idCardNumber.isBlank(),
                    )

                    CommonTextField(
                        value = driveLicense,
                        onValueChange = { driveLicense = it },
                        label = "Driver License",
                        icon = Icons.Default.Badge,
                        isError = driveLicense.isBlank(),
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "License Expiration: ${driveLicenseExpirationDate.toLocalDate()}",
                            modifier = Modifier.weight(1f)
                        )
                        Button(onClick = { showDatePicker = true}) {
                            Text("Change")
                        }
                    }

                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    Button(
                        onClick = {
                            focusManager.clearFocus()
                            val customer= CreateCustomerRequest(
                                email,
                                password,
                                name,
                                phone,
                                street,
                                state,
                                city,
                                driveLicense,
                                idCardNumber,
                                driveLicenseExpirationDate.toString()
                            )
                            onSave(customer)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        enabled = validate()
                    ) {
                        Text(text = "Save Customer")
                    }
                }
            }
        }
    }

    if (showDatePicker) {
        ShowDatePickerDialog(
            onDismiss = { showDatePicker = false },
            onDateSelected = { dateLong ->
                if(dateLong != null){
                    driveLicenseExpirationDate =AppHelpers.longToLocalDateTime(dateLong)
                }
            }
        )
    }
}
