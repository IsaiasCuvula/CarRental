package com.bersyte.rent_a_car.features.company.data.models


data class CarRegistration(
    val registrationDate: String,
    val updatedAt: String,
    val note: String = "",
    val status: String,
    val deniedReason: String = "",
    val registrationNumber: String,
    val plate: String? = null,
    val registeredBy: String,
    val canceledBy: String? = null,
    val processedBy: String? = null
)
