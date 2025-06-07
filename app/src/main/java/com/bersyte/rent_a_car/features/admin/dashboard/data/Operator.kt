package com.bersyte.rent_a_car.features.admin.dashboard.data

import java.time.LocalDateTime

data class Operator(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val dateCreated: LocalDateTime,
    val isActive: Boolean
)
