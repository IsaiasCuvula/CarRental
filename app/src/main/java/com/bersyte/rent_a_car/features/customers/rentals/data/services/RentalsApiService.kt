package com.bersyte.rent_a_car.features.customers.rentals.data.services

import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import retrofit2.http.GET

interface RentalsApiService {

    @GET("api/v1/rentals")
    suspend fun getCustomerRentals(): List<Rental>
}
