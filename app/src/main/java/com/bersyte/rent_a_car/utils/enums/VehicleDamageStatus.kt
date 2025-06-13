package com.bersyte.rent_a_car.utils.enums

enum class VehicleDamageStatus(val displayName: String) {
    PENDING("Pending"), FIXED("Fixed");

    companion object {
        fun getAllFilterOptions(): List<String> = listOf("All") + VehicleDamageStatus.entries.map { it.displayName }

        fun fromDisplayName(displayName: String): VehicleDamageStatus? {
            return VehicleDamageStatus.entries.find { it.displayName.equals(displayName, ignoreCase = true) }
        }
    }
}
