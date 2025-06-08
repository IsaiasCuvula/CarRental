package com.bersyte.rent_a_car.features.customers.rentals.data.datasource

import com.bersyte.rent_a_car.features.customers.rentals.data.services.RentalsApiService
import javax.inject.Inject

class RentalsRemoteDataSource @Inject constructor(
    private val rentalsApi: RentalsApiService
) {
    suspend fun fetchCustomerRentals() = rentalsApi.getCustomerRentals()
}
