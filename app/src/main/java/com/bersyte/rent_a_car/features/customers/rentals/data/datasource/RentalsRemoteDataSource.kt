package com.bersyte.rent_a_car.features.customers.rentals.data.datasource

import com.bersyte.rent_a_car.features.customers.rentals.data.models.CancelRental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.PostponeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.FinalizeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.StartRentingRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.services.RentalsApiService
import retrofit2.http.Body
import javax.inject.Inject

class RentalsRemoteDataSource @Inject constructor(
    private val rentalsApi: RentalsApiService
) {
    suspend fun fetchCustomerRentals() = rentalsApi.getCustomerRentals()
    suspend fun postponeRental(request: PostponeRentalRequest) = rentalsApi.postponeRental(request)
    suspend fun finalizeRental(request: FinalizeRentalRequest) = rentalsApi.finalizeRental(request)
    suspend fun startRenting(request: StartRentingRequest) = rentalsApi.startRenting(request)
    suspend fun cancelRenting(request: CancelRental) = rentalsApi.cancelRenting(request)
}
