package com.bersyte.rent_a_car.features.customers.profile.data.models

import com.bersyte.rent_a_car.features.customers.home.data.models.Address
import com.bersyte.rent_a_car.utils.enums.UserRole
import java.time.LocalDateTime

data class Customer(
    val name: String,
    val email: String,
    val phone: String,
    val role: UserRole,
    val address: Address,
    val keycloakId: String,
    val createdAt: LocalDateTime,
    val driverLicenseNumber: String?,
    val driverLicenseExpirationDate: LocalDateTime?,
    val idCardNumber: String?,
    val loyaltyPoints: Long,
    val updatedAt: LocalDateTime?
)
