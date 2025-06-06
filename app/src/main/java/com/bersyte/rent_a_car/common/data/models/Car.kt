package com.bersyte.rent_a_car.common.data.models

import java.time.LocalDateTime

data class Car(
    val color: Int,
    val smokingAllowed: Boolean,
    val seats: Int,
    val discountPercentage: Int,
    val hourlyPrice: Long,
    val feePerHourRented: Long,
    val carClass: String,
    val carType: String,
    val carStatus: String,
    val fuelType: String,
    val photos: String,
    val name: String,
    val description: String,
    val model: String,
    val year: Int,
    val plate: String,
    val address: Address,
    val ratings: List<CarRating>,
    //val rentals: List<Rental>,
    val createdAt: LocalDateTime
)
