package com.bersyte.rent_a_car.features.customers.profile.data.models

data class UpdateCustomerRequest(
    val driveLicenseExpirationDate: String,
    val name: String,
    val phone: String,
    val cityName: String,
    val idCardNumber: String,
    val driveLicense: String,
    val street: String,
    val state: String
)
