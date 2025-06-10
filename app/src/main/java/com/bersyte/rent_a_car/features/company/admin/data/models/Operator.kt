package com.bersyte.rent_a_car.features.company.admin.data.models

import com.bersyte.rent_a_car.common.data.models.Address
import com.bersyte.rent_a_car.utils.enums.UserRole

data class Operator(
    val name: String,
    val email: String,
    val phone: String?,
    val role: UserRole,
    val address: Address,
    val keycloakId: String,
    val createdAt: String
){
    override fun toString(): String {
        return mapOf(
            "name" to name,
            "email" to email,
            "phone" to  (phone ?: ""),
            "role" to role,
            "address" to address,
            "keycloakId" to keycloakId,
            "createdAt" to createdAt,
        ).toString()
    }
}
