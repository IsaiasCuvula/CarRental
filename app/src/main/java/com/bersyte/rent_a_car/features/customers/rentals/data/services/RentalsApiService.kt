package com.bersyte.rent_a_car.features.customers.rentals.data.services

import com.bersyte.rent_a_car.common.data.models.CancelRental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.PostponeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RentalsApiService {

    @GET("api/v1/rentals")
    suspend fun fetchCustomerRentals(): List<Rental>

    @POST("api/v1/rentals/postpone")
    suspend fun postponeRental(@Body request: PostponeRentalRequest): Rental

    @POST("api/v1/rentals/cancel")
    suspend fun cancelRenting(@Body request: CancelRental): Rental
}
