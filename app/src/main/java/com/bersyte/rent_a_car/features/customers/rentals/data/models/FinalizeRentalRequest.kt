package com.bersyte.rent_a_car.features.customers.rentals.data.models

data class FinalizeRentalRequest(
    val rentalCode: String,
    val returnConditionReport: String,
    val returnedMileage: Int,
    val damageStatus: String,
    val estimatedRepairCost: Int
)
