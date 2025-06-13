package com.bersyte.rent_a_car.features.company.data.models


data class VehicleDamage(
    val rentalCode: String,
    val description: String,
    val estimatedRepairCost: Long,
    val reportedAt: String,
    val fixedAt: String,
    val damageLocation: String,
    val carPlate: String,
    val markAsFixed: String?,
    val status: String
)
