package com.bersyte.rent_a_car.features.company.operator.data.models

import com.bersyte.rent_a_car.common.data.models.CarRequest
import java.time.LocalDateTime

data class CarRegistrationRequest(
    val id: String,
    val customerId: String,
    val carRequest: CarRequest,
    val status: String, // "PENDING", "APPROVED", "REJECTED"
    val submissionDate: LocalDateTime
)
