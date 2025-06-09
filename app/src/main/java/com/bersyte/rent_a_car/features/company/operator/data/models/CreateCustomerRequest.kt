package com.bersyte.rent_a_car.features.company.operator.data.models


data class CreateCustomerRequest(
    val userEmail: String,
    val password: String,
    val name: String,
    val phone: String,
    val street: String,
    val state: String,
    val cityName: String,
    val driveLicense: String,
    val idCardNumber: String,
    val driveLicenseExpirationDate: String
)
