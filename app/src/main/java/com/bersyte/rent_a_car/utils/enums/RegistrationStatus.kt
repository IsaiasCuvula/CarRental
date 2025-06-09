package com.bersyte.rent_a_car.utils.enums

enum class RegistrationStatus(val description: String) {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled"),
    EXPIRED("Expired"),
    SUSPENDED("Suspended");

    companion object {
        fun getAllFilterOptions(): List<String> = listOf("All") + RegistrationStatus.entries.map { it.name }

        fun fromDisplayName(displayName: String): RegistrationStatus? {
            return RegistrationStatus.entries.find { it.name.equals(displayName, ignoreCase = true) }
        }
    }
}
