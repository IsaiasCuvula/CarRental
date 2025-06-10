package com.bersyte.rent_a_car.features.company.data.models

data class FinalizeRentalRequest(
    val rentalCode: String,
    val returnConditionReport: String,
    val returnedMileage: Long,
    val damageStatus: String,
    val damageDescription: String,
    val estimatedRepairCost: Long = 0,
    val damageLocation: String
)
