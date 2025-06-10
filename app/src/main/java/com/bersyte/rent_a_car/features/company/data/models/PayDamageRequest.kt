package com.bersyte.rent_a_car.features.company.data.models


data class PayDamageRequest(
    val carPlate: String,
    val amount: String,
    val paidAt: String
)
