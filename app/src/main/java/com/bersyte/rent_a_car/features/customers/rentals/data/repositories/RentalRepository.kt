package com.bersyte.rent_a_car.features.customers.rentals.data.repositories

import com.bersyte.rent_a_car.common.data.models.CancelRental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.CarRatingRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.PostponeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.services.RentalsApiService
import javax.inject.Inject


class RentalRepository @Inject constructor(
    private val apiService: RentalsApiService
) {

   suspend fun getAllRentals() = apiService.fetchCustomerRentals()
   suspend fun addReview(request : CarRatingRequest) = apiService.addReview(request)
   suspend fun postponeRental(request: PostponeRentalRequest) = apiService.postponeRental(request)
   suspend fun cancelRenting(request: CancelRental)=apiService.cancelRenting(request)
}
