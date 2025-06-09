package com.bersyte.rent_a_car.features.customers.my_cars.data.services

import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface MyCarsApiService {
    @POST("api/v1/cars")
    suspend fun saveCar(@Body carRequest: CarRequest): Car
}
