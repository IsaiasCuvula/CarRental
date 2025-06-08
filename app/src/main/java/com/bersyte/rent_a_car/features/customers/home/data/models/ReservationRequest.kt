package com.bersyte.rent_a_car.features.customers.home.data.models

data class ReservationRequest(
    val carPlate: String,
    val startDate:  String,
    val endDate:  String,
    val isPaid: Boolean
)
