package com.bersyte.rent_a_car.features.company.admin.data.models

import java.time.LocalDateTime

data class Operator(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val dateCreated: LocalDateTime,
    val isActive: Boolean
)
