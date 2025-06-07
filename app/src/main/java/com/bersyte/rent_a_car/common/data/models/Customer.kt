package com.bersyte.rent_a_car.common.data.models

import java.time.LocalDateTime

data class Customer(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val joinDate: LocalDateTime,
    val idCardNumber: String,
    val driveLicense: String
)
