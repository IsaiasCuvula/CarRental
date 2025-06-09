package com.bersyte.rent_a_car.features.customers.rentals.data.repositories

import com.bersyte.rent_a_car.features.customers.rentals.data.datasource.RentalsRemoteDataSource
import com.bersyte.rent_a_car.common.data.models.CancelRental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.PostponeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import javax.inject.Inject

interface RentalRepository {
    suspend fun getAllRentals(): List<Rental>
    suspend fun postponeRental(request: PostponeRentalRequest): Rental
    suspend fun cancelRenting(request: CancelRental): Rental

}

class RentalRepositoryImpl @Inject constructor(
    private val remoteDataSource: RentalsRemoteDataSource
) : RentalRepository {

    override suspend fun getAllRentals() =
         remoteDataSource.fetchCustomerRentals()

   override suspend fun postponeRental(request: PostponeRentalRequest) = remoteDataSource.postponeRental(request)
   override suspend fun cancelRenting(request: CancelRental)=remoteDataSource.cancelRenting(request)
}
