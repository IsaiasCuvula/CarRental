package com.bersyte.rent_a_car.features.company.admin.data.models

import com.bersyte.rent_a_car.common.data.models.Address
import com.bersyte.rent_a_car.utils.enums.UserRole

data class Operator(
    val name: String,
    val email: String,
    val phone: String,
    val role: UserRole,
    val address: Address,
    val keycloakId: String,
    val createdAt: String
)
