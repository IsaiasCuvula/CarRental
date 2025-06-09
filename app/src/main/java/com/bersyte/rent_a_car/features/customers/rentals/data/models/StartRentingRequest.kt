package com.bersyte.rent_a_car.features.customers.rentals.data.models


data class StartRentingRequest(
    val rentalCode: String,
    val initialCondition: String
)
