package com.bersyte.rent_a_car.features.customers.my_cars.data.services

import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyCarsApiService {
    @POST("api/v1/car-registrations")
    suspend fun registerCar(@Body carRequest: CarRequest): Car

    @GET("api/v1/cars")
    suspend fun getAllCars(): List<Car>

    @GET("api/v1/rentals")
    suspend fun fetchCustomerRentals(): List<Rental>
}
