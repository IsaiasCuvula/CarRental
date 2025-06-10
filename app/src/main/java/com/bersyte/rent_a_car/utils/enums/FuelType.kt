package com.bersyte.rent_a_car.utils.enums

enum class FuelType(val displayName: String) {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid"),
    FLEX("Flex (Gasoline/Ethanol)"),
    HYDROGEN("Hydrogen");

    companion object {
        fun getAllFilterOptions(): List<String> = listOf("All") + FuelType.entries.map { it.name }

        fun fromDisplayName(displayName: String): FuelType? {
            return FuelType.entries.find { it.name.equals(displayName, ignoreCase = true) }
        }
    }
}
