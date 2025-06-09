package com.bersyte.rent_a_car.utils.enums

enum class CarType(val displayName: String) {
    SEDAN("Sedan"),
    SUV("SUV"),
    HATCHBACK("Hatchback"),
    COUPE("Coupe");

    companion object {
        fun getAllFilterOptions(): List<String> = listOf("All") + entries.map { it.displayName }

        fun fromDisplayName(displayName: String): CarType? {
            return entries.find { it.displayName.equals(displayName, ignoreCase = true) }
        }
    }
}
