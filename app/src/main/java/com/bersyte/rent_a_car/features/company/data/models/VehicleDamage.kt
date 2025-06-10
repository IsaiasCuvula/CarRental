package com.bersyte.rent_a_car.features.company.data.models

import com.bersyte.rent_a_car.utils.enums.VehicleDamageStatus
import java.time.LocalDateTime

data class VehicleDamage(
    val id: Long? = null,
    val description: String,
    val estimatedRepairCost: Long,
    val isPaid: Boolean,
    val paidAt: LocalDateTime,
    val reportedAt: LocalDateTime,
    val fixedAt: LocalDateTime,
    val damageLocation: String,
    val rentalId: Long,
    val carId: Long,
    val markAsPaidByEmployeeId: Long?,
    val markAsFixedEmployeeId: Long?,
    val status: VehicleDamageStatus
)
