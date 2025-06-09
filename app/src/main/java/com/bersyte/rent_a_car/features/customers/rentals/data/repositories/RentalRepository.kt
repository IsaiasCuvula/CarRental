package com.bersyte.rent_a_car.features.customers.rentals.data.repositories

import com.bersyte.rent_a_car.features.customers.rentals.data.datasource.RentalsRemoteDataSource
import com.bersyte.rent_a_car.features.customers.rentals.data.models.CancelRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.FinalizeRentalRequest
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.StartRentingRequest
import javax.inject.Inject

interface RentalRepository {
    suspend fun getAllRentals(): List<Rental>
}

class RentalRepositoryImpl @Inject constructor(
    private val remoteDataSource: RentalsRemoteDataSource
) : RentalRepository {

    override suspend fun getAllRentals() =
         remoteDataSource.fetchCustomerRentals()

    suspend fun postponeRental(request: CancelRentalRequest) = remoteDataSource.postponeRental(request)
    suspend fun finalizeRental(request: FinalizeRentalRequest) = remoteDataSource.finalizeRental(request)
    suspend fun startRenting(request: StartRentingRequest) = remoteDataSource.startRenting(request)
}
