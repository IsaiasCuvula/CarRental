package com.bersyte.rent_a_car.features.company.operator.data.models

data class CreateOperatorRequest(
    val userEmail: String,
    val password: String,
    val name: String,
    val phone: String,
    val street: String,
    val state: String,
    val cityName: String,
)
