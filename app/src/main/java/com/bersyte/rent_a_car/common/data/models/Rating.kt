package com.bersyte.rent_a_car.common.data.models

data class Rating(
    val rating: Double,
    val comment: String? = null,
    val userName: String? = null
)
