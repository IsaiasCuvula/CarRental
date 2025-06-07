package com.bersyte.rent_a_car.features.auth.data.models

data class AuthResponse(
    val email: String,
    val token: String,
    val role: String
)
