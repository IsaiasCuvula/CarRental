package com.bersyte.rent_a_car.utils.enums

enum class RentalStatus(val description: String) {
    ALL("All rentals"),
    RESERVED("Temporarily paused or reserved"),
    ACTIVE("Currently rented and in use"),
    COMPLETED("Rental period completed"),
    CANCELLED("Rental cancelled before completion")
}
