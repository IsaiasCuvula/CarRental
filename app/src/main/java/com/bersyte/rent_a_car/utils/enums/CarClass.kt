package com.bersyte.rent_a_car.utils.enums

enum class CarClass {
    URBAN, LUXURY, SUV;

    companion object {
        fun getAllFilterOptions(): List<String> = listOf("All") + CarClass.entries.map { it.name }

        fun fromDisplayName(displayName: String): CarClass? {
            return CarClass.entries.find { it.name.equals(displayName, ignoreCase = true) }
        }
    }
}
