package com.bersyte.rent_a_car.features.auth.data.models

data class SignUpRequest(
    val email: String,
    val password: String,
    val street: String,
    val state: String,
    val cityName: String
)
