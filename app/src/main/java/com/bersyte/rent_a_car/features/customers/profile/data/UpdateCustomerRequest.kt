package com.bersyte.rent_a_car.features.customers.profile.data

import java.time.LocalDateTime

data class UpdateCustomerRequest(
    val name: String,
    val phone: String,
    val cityName: String,
    val idCardNumber: String,
    val driveLicense: String,
    val driveLicenseExpirationDate: LocalDateTime,
    val street: String,
    val state: String
)
