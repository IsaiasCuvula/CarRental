package com.bersyte.rent_a_car.features.auth.data.models

data class SignUpRequest(
    val email: String,
    val password: String,
    val role: String,
    val cityName: String
)
