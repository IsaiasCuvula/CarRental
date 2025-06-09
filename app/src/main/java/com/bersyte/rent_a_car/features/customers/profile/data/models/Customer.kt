package com.bersyte.rent_a_car.features.customers.profile.data.models

import com.bersyte.rent_a_car.common.data.models.Address
import com.bersyte.rent_a_car.utils.enums.UserRole

data class Customer(
    val name: String,
    val email: String,
    val phone: String?,
    val role: UserRole,
    val address: Address,
    val keycloakId: String,
    val createdAt: String,
    val driverLicenseNumber: String?,
    val driverLicenseExpirationDate: String?,
    val idCardNumber: String?,
    val loyaltyPoints: Long,
    val updatedAt: String?
) {
    override fun toString(): String {
        return mapOf(
            "name" to name,
            "email" to email,
            "phone" to  (phone ?: ""),
            "role" to role,
            "address" to address,
            "keycloakId" to keycloakId,
            "createdAt" to createdAt,
            "driverLicenseNumber" to (driverLicenseNumber ?: ""),
            "driverLicenseExpirationDate" to (driverLicenseExpirationDate ?: ""),
            "idCardNumber" to (idCardNumber ?: ""),
            "loyaltyPoints" to loyaltyPoints,
            "updatedAt" to (updatedAt ?: "")
        ).toString()
    }
}
