package com.bersyte.rent_a_car.features.company.operator.data.models
import com.bersyte.rent_a_car.utils.enums.RentalStatus
import java.time.LocalDateTime

data class FinalizeRentalRequest(
    val rentalCode: String,
    val returnConditionReport: String,
    val returnedMileage: Long,
    val rentalStatus: RentalStatus,
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val actualReturnDate: LocalDateTime = LocalDateTime.now(),
    val damageStatus: String,
    val damageDescription: String,
    val estimatedRepairCost: Long = 0,
    val damageLocation: String
)
