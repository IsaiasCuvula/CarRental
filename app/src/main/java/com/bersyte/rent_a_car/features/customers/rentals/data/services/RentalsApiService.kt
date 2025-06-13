package com.bersyte.rent_a_car.features.customers.rentals.data.services

import com.bersyte.rent_a_car.common.data.models.CancelRental
import com.bersyte.rent_a_car.features.customers.home.data.models.CarRating
import com.bersyte.rent_a_car.features.customers.rentals.data.models.CarRatingRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.PostponeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RentalsApiService {

    @POST("api/v1/ratings")
    suspend fun addReview(@Body request : CarRatingRequest): CarRating

    @GET("api/v1/rentals")
    suspend fun fetchCustomerRentals(): List<Rental>

    @POST("api/v1/rentals/postpone")
    suspend fun postponeRental(@Body request: PostponeRentalRequest): Rental

    @POST("api/v1/rentals/customer/cancel")
    suspend fun cancelRenting(@Body request: CancelRental): Rental
}
