package com.bersyte.rent_a_car.common.data.models


data class City(
    val id: Int,
    val name: String,
    val country: Country,
    val postcode: String,
    val createdAt: String
)
