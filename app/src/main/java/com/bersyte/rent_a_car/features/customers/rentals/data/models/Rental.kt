package com.bersyte.rent_a_car.features.customers.rentals.data.models


data class Rental(
    val rentalCode: String,
    val carName: String,
    val carPlate: String,
    val carSeats: Int,
    val rentStartDate: String,
    val rentEndDate: String,
    val formattedAmount: String,
     val totalPaidAmount: Int,
    val status: String
)
