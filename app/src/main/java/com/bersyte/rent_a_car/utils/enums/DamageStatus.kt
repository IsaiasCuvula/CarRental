package com.bersyte.rent_a_car.utils.enums

enum class DamageStatus(val description: String) {
    NONE("Car is in the same conditions as it was before renting"),
    MINOR_DAMAGE("Car returned with minor cosmetic damage"),
    MODERATE_DAMAGED ("Car returned with damage requiring repair"),
    SEVERE_DAMAGE("Car returned with significant damage"),
    TOTAL_LOSS("Car returned with damage exceeding its value");
}
