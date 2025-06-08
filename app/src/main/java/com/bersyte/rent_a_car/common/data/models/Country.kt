package com.bersyte.rent_a_car.common.data.models

import java.time.LocalDateTime

data class Country(
    val id: Int,
    val name: String,
    val code: String,
    val shortName: String,
    val flag: String,
    val officialLanguage: String,
    val createdAt: String
)
