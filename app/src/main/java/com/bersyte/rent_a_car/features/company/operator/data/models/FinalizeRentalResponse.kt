package com.bersyte.rent_a_car.features.company.operator.data.models

import java.time.LocalDateTime

data class FinalizeRentalResponse(
    val rentalCode: String,
    val carPlate: String,
    val customerName: String,
    val startDate: String,
    val endDate: String,
    val actualReturnDate: String,
    val initialMileage: Double,
    val returnedMileage: Double,
    val returnStatus: String,
    val totalAmount: Long,
    val delayedFee: Long = 0,
    val isPaid: Boolean = false,
    val damageStatus: String? = null
)
