package com.bersyte.rent_a_car.features.customers.rentals.data.models

data class PostponeRentalRequest(
    val rentalCode: String,
    val newStartDate: String
)
