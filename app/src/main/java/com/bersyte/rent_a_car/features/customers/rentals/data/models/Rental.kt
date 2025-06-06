package com.bersyte.rent_a_car.features.customers.rentals.data.models
import java.time.LocalDateTime

data class Rental(
    val rentalCode: String,
    val carName: String,
    val carPlate: String,
    val carSeats: Int,
    val rentStartDate: LocalDateTime,
    val rentEndDate: LocalDateTime,
    val totalPaidAmount: String,
    val status: String
)
