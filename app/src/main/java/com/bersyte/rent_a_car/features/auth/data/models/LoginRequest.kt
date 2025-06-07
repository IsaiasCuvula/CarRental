package com.bersyte.rent_a_car.features.auth.data.models

data class LoginRequest(
    val email: String,
    val password: String
)
