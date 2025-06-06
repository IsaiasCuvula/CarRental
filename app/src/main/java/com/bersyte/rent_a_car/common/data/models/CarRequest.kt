package com.bersyte.rent_a_car.common.data.models

import com.bersyte.rent_a_car.utils.enums.CarStatus
import java.time.LocalDateTime

data class CarRequest(
    val color: Int,
    val smokingAllowed: Boolean,
    val seats: Int,
    val hourlyPrice: Long,
    val carClass: String,
    val carType: String,
    val fuelType: String,
    val name: String,
    val description: String,
    val model: String,
    val year: Int,
    val plate: String,
    val mileage: Long,
    val cityName: String,
    val street: String,
    val state: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val carStatus: CarStatus = CarStatus.AVAILABLE
)
