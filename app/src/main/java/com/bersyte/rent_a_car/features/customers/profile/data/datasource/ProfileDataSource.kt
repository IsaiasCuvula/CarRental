package com.bersyte.rent_a_car.features.customers.profile.data.datasource

import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.features.customers.profile.data.models.UpdateCustomerRequest
import com.bersyte.rent_a_car.features.customers.profile.data.services.ProfileApiService
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    private val apiService: ProfileApiService
) {
    suspend fun getCustomer(): Customer =
        apiService.getCustomer()

    suspend fun updateCustomer(request: UpdateCustomerRequest): Customer =
        apiService.updateCustomer(request)
}
