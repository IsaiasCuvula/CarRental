package com.bersyte.rent_a_car.features.customers.home.data.models

data class CarRating(
    val rating: Double,
    val comment: String? = null,
    val userName: String? = null,
    val ratingDate: String
)
