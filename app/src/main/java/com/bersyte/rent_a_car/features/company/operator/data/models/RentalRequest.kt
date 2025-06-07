package com.bersyte.rent_a_car.features.company.operator.data.models

import java.time.LocalDateTime

data class RentalRequest(
    val id: String,
    val rentalCode: String,
    val customerId: String,
    val carId: String,
    val status: String, // "PENDING", "APPROVED", "REJECTED"
    val requestDate: LocalDateTime
)
