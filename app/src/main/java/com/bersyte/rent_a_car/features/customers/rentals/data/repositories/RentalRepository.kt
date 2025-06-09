package com.bersyte.rent_a_car.features.customers.rentals.data.repositories

import com.bersyte.rent_a_car.features.customers.rentals.data.datasource.RentalsRemoteDataSource
import com.bersyte.rent_a_car.features.customers.rentals.data.models.CancelRental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.PostponeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.FinalizeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.StartRentingRequest
import javax.inject.Inject

interface RentalRepository {
    suspend fun getAllRentals(): List<Rental>
    suspend fun postponeRental(request: PostponeRentalRequest): Rental
    suspend fun finalizeRental(request: FinalizeRentalRequest):Rental
    suspend fun startRenting(request: StartRentingRequest):Rental
    suspend fun cancelRenting(request: CancelRental): Rental

}

class RentalRepositoryImpl @Inject constructor(
    private val remoteDataSource: RentalsRemoteDataSource
) : RentalRepository {

    override suspend fun getAllRentals() =
         remoteDataSource.fetchCustomerRentals()

   override suspend fun postponeRental(request: PostponeRentalRequest) = remoteDataSource.postponeRental(request)
   override suspend fun finalizeRental(request: FinalizeRentalRequest) = remoteDataSource.finalizeRental(request)
   override suspend fun startRenting(request: StartRentingRequest) = remoteDataSource.startRenting(request)
   override suspend fun cancelRenting(request: CancelRental)=remoteDataSource.cancelRenting(request)
}
