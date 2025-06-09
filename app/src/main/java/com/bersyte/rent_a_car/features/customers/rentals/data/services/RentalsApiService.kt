package com.bersyte.rent_a_car.features.customers.rentals.data.services

import com.bersyte.rent_a_car.features.customers.rentals.data.models.CancelRental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.PostponeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.FinalizeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.StartRentingRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RentalsApiService {

    @GET("api/v1/rentals")
    suspend fun getCustomerRentals(): List<Rental>

    @POST("api/v1/rentals/postpone")
    suspend fun postponeRental(@Body request: PostponeRentalRequest): Rental

    @POST("api/v1/rentals/finalize")
    suspend fun finalizeRental(@Body request: FinalizeRentalRequest): Rental

    @POST("api/v1/rentals/renting")
    suspend fun startRenting(@Body request: StartRentingRequest): Rental

    @POST("api/v1/rentals/cancel")
    suspend fun cancelRenting(@Body request: CancelRental): Rental
}
