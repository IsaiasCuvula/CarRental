package com.bersyte.rent_a_car.features.company.data.models

data class UpdateCarRegistrationStatus(
     val registrationNumber: String,
     val plate: String,
     val status: String,
)
