package com.bersyte.rent_a_car.features.customers.rentals.data.repositories

import com.bersyte.rent_a_car.features.customers.rentals.data.datasource.RentalsRemoteDataSource
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import javax.inject.Inject

interface RentalRepository {
    suspend fun getAllRentals(): List<Rental>
}

class RentalRepositoryImpl @Inject constructor(
    private val remoteDataSource: RentalsRemoteDataSource
) : RentalRepository {

    override suspend fun getAllRentals() =
         remoteDataSource.fetchCustomerRentals()
}
