package com.bersyte.rent_a_car.features.customers.rentals.data.models

data class CarRatingRequest(
    val rating: Int,
    val comment: String,
    val plate: String,
    val rentalCode: String
)
